package com.example.roomexamples.datasource.local.entity.composite.manytomany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.datasource.local.entity.Student
import com.example.roomexamples.datasource.local.entity.StudentArticleCrossRef

data class ArticleAndStudent(
    @Embedded
    val article: Article,
    @Relation(
        parentColumn = "article_id",
        entityColumn = "student_id",
        associateBy = Junction(StudentArticleCrossRef::class)
    )
    val student: List<Student>
)