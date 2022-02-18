package com.example.roomexamples.datasource.local.entity.composite.manytomany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.datasource.local.entity.Student
import com.example.roomexamples.datasource.local.entity.StudentArticleCrossRef

data class StudentAndArticle(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "student_id",
        entityColumn = "article_id",
        associateBy = Junction(StudentArticleCrossRef::class)
    )
    val article: List<Article>
)