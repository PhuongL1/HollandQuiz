package com.example.hollandquiz.utils

import android.content.Context
import com.example.hollandquiz.R
import com.example.hollandquiz.models.Question
import org.json.JSONArray
import org.json.JSONObject

object JsonHelper {

    // Đọc dữ liệu từ questions.json
    fun loadQuestions(context: Context): List<Question> {
        val inputStream = context.resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonText)

        val questions = mutableListOf<Question>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            questions.add(
                Question(
                    id = jsonObject.getInt("id"),
                    text = jsonObject.getString("question"),
                    category = jsonObject.getString("category")
                )
            )
        }
        return questions
    }

    // Đọc dữ liệu từ career_groups.json
    fun loadCareerGroups(context: Context): List<JSONObject> {
        val inputStream = context.resources.openRawResource(R.raw.career_groups)
        val jsonText = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonText)

        val careerGroups = mutableListOf<JSONObject>()
        for (i in 0 until jsonArray.length()) {
            careerGroups.add(jsonArray.getJSONObject(i))
        }
        return careerGroups
    }
}
