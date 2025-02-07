package com.example.hollandquiz.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Chuyển đến QuizActivity
        binding.btnStartQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        // Chuyển đến GuideActivity
        binding.btnGuide.setOnClickListener {
            startActivity(Intent(this, GuideActivity::class.java))
        }

        // Chuyển đến LibraryActivity
        binding.btnCareers.setOnClickListener {
            startActivity(Intent(this, LibraryActivity::class.java))
        }

        // Thoát ứng dụng
        binding.btnExit.setOnClickListener {
            finish()
        }
    }
}
