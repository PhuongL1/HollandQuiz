package com.example.hollandquiz.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hollandquiz.models.Question
import com.example.hollandquiz.utils.JsonHelper

class QuizViewModel(context: Context) : ViewModel() {

    private val questions: List<Question> = JsonHelper.loadQuestions(context)
    private var currentIndex = 0
    private val scores = IntArray(6) // 6 nhóm câu hỏi
    private var answeredQuestions = mutableSetOf<Int>() // Lưu trữ các câu hỏi đã trả lời

    // Lấy câu hỏi hiện tại
    fun getCurrentQuestion(): Question = questions[currentIndex]

    // Chuyển đến câu hỏi tiếp theo
    fun nextQuestion() {
        if (currentIndex < questions.size - 1) {
            currentIndex++
        }
    }

    // Chuyển đến câu hỏi trước đó
    fun previousQuestion() {
        if (currentIndex > 0) {
            currentIndex--
        }
    }

    // Gửi đáp án và cập nhật trạng thái
    fun submitAnswer(answer: Int) {
        val groupIndex = getGroupIndexForCurrentQuestion()

        // Cập nhật điểm nhóm
        scores[groupIndex] += answer

        // Đánh dấu câu hỏi hiện tại là đã trả lời
        answeredQuestions.add(currentIndex)
    }

    // Kiểm tra nếu câu hỏi đã được trả lời
    fun isAnswered(questionIndex: Int): Boolean = questionIndex in answeredQuestions

    // Kiểm tra nếu tất cả câu hỏi đã được trả lời
    fun isAllAnswered(): Boolean = answeredQuestions.size == questions.size

    // Kiểm tra nếu đang ở câu hỏi đầu tiên
    fun isFirstQuestion(): Boolean = currentIndex == 0

    // Kiểm tra nếu đang ở câu hỏi cuối cùng
    fun isLastQuestion(): Boolean = currentIndex == questions.size - 1

    // Lấy điểm số của tất cả các nhóm
    fun getScores(): IntArray = scores

    // Lấy tổng số câu hỏi
    fun getTotalQuestions(): Int = questions.size

    // Lấy chỉ số câu hỏi hiện tại
    fun getCurrentQuestionIndex(): Int = currentIndex

    // Lấy chỉ số nhóm dựa trên câu hỏi hiện tại
    private fun getGroupIndexForCurrentQuestion(): Int {
        // Ví dụ: mỗi nhóm có số câu bằng nhau, 6 nhóm, tổng số câu chia đều
        val questionsPerGroup = questions.size / 6
        return currentIndex / questionsPerGroup
    }

    // Đặt lại trạng thái
    fun resetQuiz() {
        currentIndex = 0
        scores.fill(0)
        answeredQuestions.clear()
    }
}
