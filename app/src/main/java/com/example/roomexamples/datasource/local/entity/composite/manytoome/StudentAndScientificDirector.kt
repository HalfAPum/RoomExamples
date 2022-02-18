package com.example.roomexamples.datasource.local.entity.composite.manytoome

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Lecturer
import com.example.roomexamples.datasource.local.entity.Student

data class StudentAndScientificDirector(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "scientific_director_id",
        entityColumn = "lecturer_id",
    )
    val lecturer: Lecturer,
)