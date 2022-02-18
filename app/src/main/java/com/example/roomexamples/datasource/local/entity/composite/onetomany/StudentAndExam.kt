package com.example.roomexamples.datasource.local.entity.composite.onetomany

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Exam
import com.example.roomexamples.datasource.local.entity.Student

data class StudentAndExam(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "student_id",
        entityColumn = "examined_student_id",
    )
    val exams: List<Exam>
)