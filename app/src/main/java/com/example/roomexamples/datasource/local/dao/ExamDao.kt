package com.example.roomexamples.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roomexamples.datasource.local.entity.Exam
import com.example.roomexamples.datasource.local.entity.composite.manytoome.ExamAndExaminator
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamDao : BaseDao<Exam> {

    //Base functions

    @Query("SELECT * FROM Exam")
    suspend fun getExams() : List<Exam>

    @Query("SELECT * FROM Exam")
    fun getExamsRx() : Single<List<Exam>>

    @Query("SELECT * FROM Exam")
    fun getExamsRxContinuous() : Observable<List<Exam>>

    @Query("SELECT * FROM Exam")
    fun getExamsContinuous() : Flow<List<Exam>>

    //Composite functions

    @Transaction
    @Query("SELECT * FROM Exam")
    suspend fun getExamAndLecturer() : List<ExamAndExaminator>

}