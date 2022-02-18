package com.example.roomexamples.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.datasource.local.entity.Diploma

@Dao
interface DiplomaDao : BaseDao<Diploma> {

    @Query("SELECT * FROM Diploma")
    suspend fun getDiploma() : List<Diploma>

}