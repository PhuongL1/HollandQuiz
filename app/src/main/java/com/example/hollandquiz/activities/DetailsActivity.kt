package com.example.hollandquiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityDetailsBinding
import org.json.JSONArray

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng View Binding
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Intent
        val groupName = intent.getStringExtra("groupName") ?: "Nhóm không xác định"
        val groupDescription = intent.getStringExtra("groupDescription") ?: "Không có mô tả"
        val careersJson = intent.getStringExtra("careers") ?: "[]"

        // Chuyển đổi danh sách nghề nghiệp từ chuỗi JSON
        val careers = StringBuilder()
        try {
            val jsonArray = JSONArray(careersJson)
            for (i in 0 until jsonArray.length()) {
                careers.append("- ").append(jsonArray.getString(i)).append("\n")
            }
        } catch (e: Exception) {
            careers.append("Không có thông tin nghề nghiệp.")
        }

        // Hiển thị thông tin trên giao diện
        binding.tvGroupName.text = groupName
        binding.tvGroupDescription.text = groupDescription
        binding.tvCareers.text = careers.toString()

        // Nút "Quay lại"
        binding.btnBack.setOnClickListener {
            finish() // Đóng màn hình hiện tại và quay lại kết quả
        }
    }
}
