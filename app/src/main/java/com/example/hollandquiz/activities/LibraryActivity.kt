package com.example.hollandquiz.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.R
import com.example.hollandquiz.databinding.ActivityLibraryBinding
import org.json.JSONArray
import org.json.JSONObject

class LibraryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng View Binding
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Đọc dữ liệu từ JSON
        val careerGroups = loadCareerGroups()

        // Tạo nút cho mỗi nhóm nghề nghiệp
        careerGroups.forEach { group ->
            val button = Button(this).apply {
                text = group.getString("group")
                setBackgroundColor(Color.parseColor(group.getString("color")))
                setTextColor(Color.WHITE)
                textSize = 16f
                setPadding(16, 16, 16, 16)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 0)
                }
                setOnClickListener {
                    val intent = Intent(this@LibraryActivity, CareerDetailsActivity::class.java)
                    intent.putExtra("group", group.toString()) // Truyền toàn bộ nhóm qua Intent
                    startActivity(intent)
                }
            }
            binding.buttonContainer.addView(button)
        }

        // Xử lý sự kiện nút "Quay lại"
        binding.btnBack.setOnClickListener {
            finish() // Quay lại màn hình trước đó
        }
    }

    private fun loadCareerGroups(): List<JSONObject> {
        val inputStream = resources.openRawResource(R.raw.careers)
        val jsonText = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonText)
        val careerGroups = mutableListOf<JSONObject>()
        for (i in 0 until jsonArray.length()) {
            careerGroups.add(jsonArray.getJSONObject(i))
        }
        return careerGroups
    }
}
