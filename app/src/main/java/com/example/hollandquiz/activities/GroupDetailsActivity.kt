package com.example.hollandquiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityGroupDetailsBinding
import org.json.JSONObject

class GroupDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGroupDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng View Binding
        binding = ActivityGroupDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Intent
        val groupJson = intent.getStringExtra("group") ?: return
        val group = JSONObject(groupJson)

        // Hiển thị thông tin nhóm
        binding.tvGroupName.text = group.getString("group")
        binding.tvGroupDescription.text = group.getString("description")

        // Xử lý sự kiện nút "Quay lại"
        binding.btnBack.setOnClickListener {
            finish() // Quay lại màn hình trước đó
        }
    }
}
