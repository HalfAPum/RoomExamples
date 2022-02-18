package com.example.roomexamples.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roomexamples.datasource.local.entity.Student
import com.example.roomexamples.datasource.local.entity.composite.manytomany.StudentAndArticle
import com.example.roomexamples.datasource.local.entity.composite.onetoome.StudentAndDiploma
import com.example.roomexamples.datasource.local.entity.composite.onetomany.StudentAndExam
import com.example.roomexamples.datasource.local.entity.composite.manytoome.StudentAndScientificDirector

@Dao
interface StudentDao : BaseDao<Student> {

    //Base student functions

    @Query("SELECT * FROM Student")
    suspend fun getStudents() : List<Student>

    //Composite student functions

    @Transaction
    @Query("SELECT * FROM Student")
    suspend fun getStudentAndDiploma() : List<StudentAndDiploma>

    @Transaction
    @Query("SELECT * FROM Student")
    suspend fun getStudentAndLecturer() : List<StudentAndScientificDirector>

    @Transaction
    @Query("SELECT * FROM Student")
    suspend fun getStudentAndExam() : List<StudentAndExam>

    @Transaction
    @Query("SELECT * FROM Student")
    suspend fun getStudentAndArticle() : List<StudentAndArticle>

}