package com.example.hollandquiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityAnalysisBinding
import com.example.hollandquiz.utils.JsonHelper

class AnalysisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnalysisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scores = intent.getIntArrayExtra("scores") ?: intArrayOf(0, 0, 0, 0, 0, 0)
        val careerGroups = JsonHelper.loadCareerGroups(this)

        val strengths = StringBuilder("Điểm mạnh:\n")
        val weaknesses = StringBuilder("Điểm yếu:\n")

        scores.forEachIndexed { index, score ->
            val group = careerGroups[index].getString("group")
            if (score > 20) {
                strengths.append("- $group: $score/36\n")
            } else {
                weaknesses.append("- $group: $score/36\n")
            }
        }

        binding.tvStrengths.text = strengths.toString()
        binding.tvWeaknesses.text = weaknesses.toString()
    }
}
