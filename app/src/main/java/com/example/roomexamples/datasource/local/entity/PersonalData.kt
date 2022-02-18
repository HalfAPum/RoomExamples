package com.example.roomexamples.datasource.local.entity

import androidx.room.ColumnInfo

data class PersonalData(
    @ColumnInfo(name = "first_name")
    val firstName: String?,
    @ColumnInfo(name = "last_name")
    val lastName: String?,
    @ColumnInfo(name = "birth_date")
    val birthDate: String?,
    @ColumnInfo(name = "telephone")
    val telephone: String?,
)