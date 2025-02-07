package com.example.hollandquiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityCareerDetailsBinding
import org.json.JSONObject

class CareerDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng View Binding
        binding = ActivityCareerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu nhóm từ Intent
        val groupJson = intent.getStringExtra("group") ?: return
        val group = JSONObject(groupJson)

        // Hiển thị chi tiết nghề nghiệp
        val careers = group.getJSONArray("careers")
        val details = StringBuilder()
        for (i in 0 until careers.length()) {
            val career = careers.getJSONObject(i)
            details.append("Nghề: ${career.getString("name")}\n")
            details.append("Kỹ năng: ${career.getString("skills")}\n")
            details.append("Lương: ${career.getString("salary")}\n")
            details.append("Xu hướng: ${career.getString("growth")}\n\n")
        }
        binding.tvCareerDetails.text = details.toString()

        // Thêm logic cho nút quay lại
        binding.btnBack.setOnClickListener {
            finish() // Đóng màn hình hiện tại để quay về màn hình trước đó
        }
    }
}
