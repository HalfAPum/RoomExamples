package com.example.roomexamples.datasource.local.entity.composite.onetoome

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Diploma
import com.example.roomexamples.datasource.local.entity.Student

data class StudentAndDiploma(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "student_id",
        entityColumn = "diploma_student_id"
    )
    val diploma: Diploma
)