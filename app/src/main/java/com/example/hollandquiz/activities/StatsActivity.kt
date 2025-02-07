package com.example.hollandquiz.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hollandquiz.databinding.ActivityStatsBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class StatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding

    // Màu sắc và nhóm tương ứng
    private val groupData = listOf(
        "Realistic" to 0xFFE57373.toInt(), // Red
        "Investigative" to 0xFF64B5F6.toInt(), // Blue
        "Artistic" to 0xFF81C784.toInt(), // Green
        "Social" to 0xFFFFD54F.toInt(), // Yellow
        "Enterprising" to 0xFFFF8A65.toInt(), // Orange
        "Conventional" to 0xFF9575CD.toInt()  // Purple
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng View Binding
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            // Lấy dữ liệu từ Intent
            val scores = intent.getIntArrayExtra("scores")
                ?: throw IllegalArgumentException("Scores không được tìm thấy trong Intent.")

            // Thiết lập BarChart
            setupBarChart(binding.barChart, scores)

            // Hiển thị tóm tắt thống kê
            displayStatsSummary(scores)

            // Hiển thị chú thích
            setupLegend(binding.legendContainer)

        } catch (e: Exception) {
            e.printStackTrace()
            binding.tvStatsSummary.text = "Đã xảy ra lỗi khi tải thống kê. Vui lòng thử lại."
        }

        // Lắng nghe nút quay lại
        binding.btnBackToResult.setOnClickListener {
            finish() // Quay lại màn hình trước đó
        }
    }

    private fun setupBarChart(barChart: BarChart, scores: IntArray) {
        // Tạo BarEntries
        val entries = scores.mapIndexed { index, score ->
            BarEntry(index.toFloat(), score.toFloat())
        }

        // Tạo BarDataSet
        val dataSet = BarDataSet(entries, "Điểm số của các nhóm").apply {
            colors = groupData.map { it.second }
            valueTextSize = 14f
        }

        // Tạo BarData
        val data = BarData(dataSet).apply {
            barWidth = 0.9f // Độ rộng của cột
        }

        // Cấu hình BarChart
        barChart.data = data
        barChart.setFitBars(true) // Đảm bảo cột vừa với biểu đồ
        barChart.description = Description().apply { text = "Điểm số các nhóm nghề nghiệp" }
        barChart.setDrawValueAboveBar(true) // Hiển thị giá trị trên cột
        barChart.animateY(1000) // Hiệu ứng chạy từ dưới lên
        barChart.invalidate() // Làm mới biểu đồ
    }

    private fun setupLegend(container: GridLayout) {
        container.removeAllViews() // Xóa các thành phần cũ nếu có

        for ((group, color) in groupData) {
            val legendItem = TextView(this).apply {
                text = group
                textSize = 14f
                setPadding(8, 8, 8, 8)
                setBackgroundColor(color)
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0 // Chiều rộng cân bằng theo cột
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) // Chiếm 1 cột
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED)
                    setMargins(8, 8, 8, 8)
                }
            }
            container.addView(legendItem)
        }
    }


    private fun displayStatsSummary(scores: IntArray) {
        val maxScoreIndex = scores.indices.maxByOrNull { scores[it] } ?: 0
        val minScoreIndex = scores.indices.minByOrNull { scores[it] } ?: 0

        val summary = """
            Nhóm mạnh nhất: ${groupData[maxScoreIndex].first} (${scores[maxScoreIndex]} điểm)
            Nhóm yếu nhất: ${groupData[minScoreIndex].first} (${scores[minScoreIndex]} điểm)
            Tổng điểm: ${scores.sum()} / 216
            Điểm trung bình: %.2f
        """.trimIndent().format(scores.average())

        binding.tvStatsSummary.text = summary
    }
}
