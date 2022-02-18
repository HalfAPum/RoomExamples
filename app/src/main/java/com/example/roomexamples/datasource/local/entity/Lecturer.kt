package com.example.roomexamples.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lecturer(
    @PrimaryKey
    @ColumnInfo(name = "lecturer_id")
    val lecturerId: Int?,
    @ColumnInfo(name = "profession")
    val profession: String?,
    @ColumnInfo(name = "work_experience")
    val workExperience: String?,
    @Embedded
    val personalData: PersonalData?,
)