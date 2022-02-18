package com.example.roomexamples.datasource.local.entity.composite.nested

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomexamples.datasource.local.entity.Lecturer
import com.example.roomexamples.datasource.local.entity.Student
import com.example.roomexamples.datasource.local.entity.composite.manytomany.StudentAndArticle

data class LecturerStudentAndArticle(
    @Embedded
    val lecturer: Lecturer,
    @Relation(
        entity = Student::class,
        parentColumn = "lecturer_id",
        entityColumn = "scientific_director_id"
    )
    val studentAndArticle: List<StudentAndArticle>
)