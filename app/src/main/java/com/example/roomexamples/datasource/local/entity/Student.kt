package com.example.roomexamples.datasource.local.entity

import androidx.room.*

@Entity(foreignKeys = [
    ForeignKey(
        entity = Lecturer::class,
        parentColumns = ["lecturer_id"],
        childColumns = ["scientific_director_id"],
        onDelete = ForeignKey.CASCADE
    ),
])
data class Student(
    @PrimaryKey
    @ColumnInfo(name = "student_id")
    val studentId: Int?,
    @ColumnInfo(name = "group")
    val group: String?,
    @Embedded
    val personalData: PersonalData?,
    @ColumnInfo(name = "scientific_director_id")
    val scientificDirectorId: Int?,
)