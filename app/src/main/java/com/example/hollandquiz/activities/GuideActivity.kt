package com.example.hollandquiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityGuideBinding

class GuideActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using View Binding
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Display guide content
        binding.tvGuideContent.text = "Hướng dẫn cách chơi Holland Quiz:\n\nHolland Quiz là trò chơi trắc nghiệm giúp bạn khám phá bản thân và định hướng nghề nghiệp. \n\nCác mức độ trả lời:\n\n0 - Hoàn toàn không đúng\n1 - Đúng một phần nhỏ\n2 - Đúng một nửa\n3 - Đúng đa số\n4 - Hoàn toàn đúng\n\nVí dụ: \"Tôi có tính tự lập.\" Nếu bạn cảm thấy mình độc lập trong đa số trường hợp, hãy chọn 3. \n\nNhấn \"Tiếp tục\" để chuyển sang câu hỏi kế tiếp và nhấn \"Quay lại\" để xem lại câu hỏi trước."

        // Set up back button listener
        binding.btnBackToMain.setOnClickListener {
            finish()
        }
    }
}
