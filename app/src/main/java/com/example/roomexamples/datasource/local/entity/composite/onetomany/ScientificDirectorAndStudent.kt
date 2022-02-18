package com.example.roomexamples.datasource.local.entity.composite.onetomany

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Lecturer
import com.example.roomexamples.datasource.local.entity.Student

data class ScientificDirectorAndStudent(
    @Embedded
    val lecturer: Lecturer,
    @Relation(
        parentColumn = "lecturer_id",
        entityColumn = "scientific_director_id",
    )
    val students: List<Student>,
)