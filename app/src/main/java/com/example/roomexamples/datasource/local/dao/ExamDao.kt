package com.example.roomexamples.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roomexamples.datasource.local.entity.Exam
import com.example.roomexamples.datasource.local.entity.composite.manytoome.ExamAndExaminator

@Dao
interface ExamDao : BaseDao<Exam> {

    //Base functions

    @Query("SELECT * FROM Exam")
    suspend fun getExams() : List<Exam>

    //Composite functions

    @Transaction
    @Query("SELECT * FROM Exam")
    suspend fun getExamAndLecturer() : List<ExamAndExaminator>

}