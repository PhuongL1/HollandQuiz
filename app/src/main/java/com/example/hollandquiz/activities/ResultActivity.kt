package com.example.hollandquiz.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityResultBinding
import com.example.hollandquiz.utils.JsonHelper
import org.json.JSONObject

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng View Binding
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận điểm nhóm từ Intent
        val scores = intent.getIntArrayExtra("scores") ?: intArrayOf(0, 0, 0, 0, 0, 0)

        // Xác định nhóm có điểm cao nhất
        val highestScoreIndex = scores.indices.maxByOrNull { scores[it] } ?: 0

        // Tải dữ liệu nhóm nghề nghiệp từ career_groups.json
        val careerGroups = JsonHelper.loadCareerGroups(this)

        // Lấy thông tin nhóm nghề nghiệp phù hợp
        val selectedGroup: JSONObject = careerGroups[highestScoreIndex]
        val groupName = selectedGroup.getString("group")
        val groupDescription = selectedGroup.getString("description")
        val careers = selectedGroup.getJSONArray("careers")

        // Hiển thị kết quả
        val careersList = StringBuilder()
        for (i in 0 until careers.length()) {
            careersList.append("- ").append(careers.getString(i)).append("\n")
        }

        binding.tvGroupDescription.text = """
            Bạn thuộc nhóm: $groupName
            
            
        """.trimIndent()

        // Xử lý nút "Chi tiết" để xem phân tích và lời khuyên
        binding.btnDetails.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("groupName", groupName)
            intent.putExtra("groupDescription", groupDescription)
            intent.putExtra("careers", careers.toString())
            startActivity(intent)
        }

        // Xử lý nút "Thống kê" để xem thống kê nâng cao
        binding.btnStats.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            intent.putExtra("scores", scores)
            startActivity(intent)
        }

        // Nút "Quay lại màn hình chính"
        binding.btnExit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
