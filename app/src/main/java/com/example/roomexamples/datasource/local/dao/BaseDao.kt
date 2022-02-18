package com.example.roomexamples.datasource.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collection: Collection<T>)

    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun delete(collection: Collection<T>)

}