package com.example.roomexamples.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["student_id", "article_id"],
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["student_id"],
            childColumns = ["student_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Article::class,
            parentColumns = ["article_id"],
            childColumns = ["article_id"],
            onDelete = CASCADE
        ),
    ]
)
data class StudentArticleCrossRef(
    @ColumnInfo(name = "student_id")
    val studentId: Int,
    @ColumnInfo(name = "article_id")
    val articleId: Int,
)