package com.example.roomexamples.datasource.local.entity.composite.onetomany

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Exam
import com.example.roomexamples.datasource.local.entity.Lecturer

data class ExaminatorAndExam(
    @Embedded
    val lecturer: Lecturer,
    @Relation(
        parentColumn = "lecturer_id",
        entityColumn = "examinator_id"
    )
    val exams: List<Exam>,
)