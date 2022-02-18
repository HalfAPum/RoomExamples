package com.example.roomexamples.datasource.local.entity.composite.manytoome

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Exam
import com.example.roomexamples.datasource.local.entity.Lecturer

data class ExamAndExaminator(
    @Embedded
    val exam: Exam,
    @Relation(
        parentColumn = "examinator_id",
        entityColumn = "lecturer_id"
    )
    val lecturer: Lecturer,
)