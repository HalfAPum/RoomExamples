package com.example.roomexamples.datasource.local.entity

import androidx.room.*

@Entity(foreignKeys = [
    ForeignKey(
        entity = Student::class,
        parentColumns = ["student_id"],
        childColumns = ["diploma_student_id"],
        onDelete = ForeignKey.CASCADE
    ),
])
data class Diploma(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "diploma_id")
    val diplomaId: Int = 0,
    @ColumnInfo(name = "diploma_student_id")
    val studentId: Int?,
    @ColumnInfo(name = "theme")
    val theme: String?,
)