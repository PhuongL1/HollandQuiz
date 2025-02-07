package com.example.hollandquiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hollandquiz.databinding.ActivityQuizBinding
import com.example.hollandquiz.viewmodels.QuizViewModel
import com.example.hollandquiz.viewmodels.QuizViewModelFactory

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = QuizViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory)[QuizViewModel::class.java]



        displayQuestion()
        setupButtons()
    }

    private fun displayQuestion() {
        val question = viewModel.getCurrentQuestion()
        binding.tvQuestion.text = question.text


        val answers = listOf("Hoàn toàn sai", "Đúng một phần", "Đúng một nửa", "Đúng hầu hết", "Hoàn toàn đúng")
        binding.rbAnswer1.text = answers[0]
        binding.rbAnswer2.text = answers[1]
        binding.rbAnswer3.text = answers[2]
        binding.rbAnswer4.text = answers[3]
        binding.rbAnswer5.text = answers[4]

        binding.answerContainer.clearCheck()
        binding.btnBack.isEnabled = !viewModel.isFirstQuestion()


        // Ẩn nút "Quay lại" nếu đang ở câu đầu tiên
        binding.btnBack.isEnabled = !viewModel.isFirstQuestion()
    }

    private fun setupButtons() {
        binding.btnNext.setOnClickListener {
            handleNextButtonClick()
        }
        binding.btnBack.setOnClickListener {
            handleBackButtonClick()
        }
        binding.btnFinish.setOnClickListener {
            handleFinishButtonClick()
        }
    }

    private fun handleNextButtonClick() {
        if (binding.answerContainer.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Hãy chọn một đáp án!", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedAnswer = when (binding.answerContainer.checkedRadioButtonId) {
            binding.rbAnswer1.id -> 0
            binding.rbAnswer2.id -> 1
            binding.rbAnswer3.id -> 2
            binding.rbAnswer4.id -> 3
            binding.rbAnswer5.id -> 4
            else -> 0
        }
        viewModel.submitAnswer(selectedAnswer)

        if (!viewModel.isLastQuestion()) {
            viewModel.nextQuestion()
            displayQuestion() // Gọi lại displayQuestion để cập nhật UI
        } else {
            Toast.makeText(this, "Bạn đã đến câu cuối cùng. Nhấn 'kết thúc' để xem kết quả!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun handleBackButtonClick() {
        viewModel.previousQuestion()
        displayQuestion()
    }

    private fun handleFinishButtonClick() {
        val scores = viewModel.getScores()
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("scores", scores)
        startActivity(intent)
        finish()
    }
}