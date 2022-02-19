package com.example.roomexamples.datasource.local.entity

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["student_id"],
            childColumns = ["examined_student_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Lecturer::class,
            parentColumns = ["lecturer_id"],
            childColumns = ["examinator_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(
            value = ["examinator_id"],
            unique = false,
            orders = [Index.Order.ASC]
        )
    ]
)
data class Exam(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exam_id")
    val examId: Int = 0,
    @ColumnInfo(name = "examined_student_id")
    val studentId: Int?,
    @ColumnInfo(name = "subject")
    val subject: String?,
    @ColumnInfo(name = "examinator_id")
    val examinatorId: Int?,
    @ColumnInfo(name = "mark")
    val mark: Int?,
)