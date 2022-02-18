package com.example.roomexamples.datasource.local.view

import androidx.room.DatabaseView

@DatabaseView("SELECT * FROM LECTURER " +
        "INNER JOIN STUDENT ON LECTURER.lecturer_id = STUDENT.scientific_director_id " +
        "INNER JOIN StudentArticleCrossRef ON STUDENT.student_id = StudentArticleCrossRef.student_id " +
        "INNER JOIN ARTICLE ON StudentArticleCrossRef.article_id = ARTICLE.article_id ")
data class LecturerArticles(
    val lastName: String?,
    val theme: String?,
)