package com.example.hollandquiz

import org.junit.Assert.assertEquals // Sử dụng để kiểm tra giá trị mong đợi và thực tế
import org.junit.Test // Annotation để đánh dấu phương thức là test case

class QuizLogicTest {

    // Test trường hợp cơ bản
    @Test
    fun testCalculateScore_NormalCase() {
        val correctAnswers = 4
        val totalQuestions = 5

        val score = calculateScore(correctAnswers, totalQuestions)

        // Kiểm tra: 4/5 = 80.0%
        assertEquals(80.0, score, 0.001) // So sánh giá trị trả về với giá trị kỳ vọng
    }

    // Test trường hợp tất cả câu trả lời đúng
    @Test
    fun testCalculateScore_AllCorrect() {
        val correctAnswers = 10
        val totalQuestions = 10

        val score = calculateScore(correctAnswers, totalQuestions)

        // Kiểm tra: 10/10 = 100.0%
        assertEquals(100.0, score, 0.001)
    }

    // Test trường hợp không có câu trả lời đúng
    @Test
    fun testCalculateScore_AllWrong() {
        val correctAnswers = 0
        val totalQuestions = 10

        val score = calculateScore(correctAnswers, totalQuestions)

        // Kiểm tra: 0/10 = 0.0%
        assertEquals(0.0, score, 0.001)
    }

    // Test trường hợp không có câu hỏi (tổng câu hỏi = 0)
    @Test
    fun testCalculateScore_ZeroQuestions() {
        val correctAnswers = 0
        val totalQuestions = 0

        val score = calculateScore(correctAnswers, totalQuestions)

        // Kiểm tra: Trả về 0.0 khi không có câu hỏi
        assertEquals(0.0, score, 0.001)
    }

    // Test trường hợp số câu trả lời đúng nhiều hơn tổng câu hỏi (lỗi logic của đầu vào)
    @Test
    fun testCalculateScore_CorrectMoreThanTotal() {
        val correctAnswers = 12
        val totalQuestions = 10

        val score = calculateScore(correctAnswers, totalQuestions)

        // Kiểm tra: Giá trị không hợp lệ, có thể quy về 120%
        assertEquals(120.0, score, 0.001)
    }

    // Hàm kiểm thử (giả sử logic này nằm trong file chính)
    private fun calculateScore(correctAnswers: Int, totalQuestions: Int): Double {
        // Xử lý trường hợp ngoại lệ: Không có câu hỏi
        if (totalQuestions == 0) return 0.0
        // Tính điểm phần trăm
        return (correctAnswers.toDouble() / totalQuestions) * 100
    }
}
