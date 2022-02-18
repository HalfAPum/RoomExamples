package com.example.roomexamples.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.datasource.local.entity.Lecturer
import com.example.roomexamples.datasource.local.entity.Student
import com.example.roomexamples.datasource.local.entity.StudentArticleCrossRef
import com.example.roomexamples.datasource.local.entity.composite.manytomany.StudentAndArticle
import com.example.roomexamples.datasource.local.entity.composite.nested.LecturerStudentAndArticle
import com.example.roomexamples.datasource.local.entity.composite.onetomany.ExaminatorAndExam
import com.example.roomexamples.datasource.local.entity.composite.onetomany.ScientificDirectorAndStudent
import com.example.roomexamples.datasource.local.view.LecturerArticles

@Dao
interface LecturerDao : BaseDao<Lecturer> {

    //Base functions

    @Query("SELECT * FROM Lecturer")
    suspend fun getLecturers() : List<Lecturer>

    //Composite functions

    @Transaction
    @Query("SELECT * FROM Lecturer")
    suspend fun getLecturerAndStudent() : List<ScientificDirectorAndStudent>

    @Transaction
    @Query("SELECT * FROM Lecturer")
    suspend fun getLecturerAndExam() : List<ExaminatorAndExam>

    //Super composite

    @Transaction
    @Query("SELECT * FROM Lecturer")
    suspend fun getLecturerStudentAndArticle() : List<LecturerStudentAndArticle>

    //DatabaseView

    @Transaction
    @Query("SELECT * FROM LecturerArticles")
    suspend fun getLecturerArticles() : List<LecturerArticles>

    @Transaction
    @Query("SELECT * FROM LECTURER " +
            "INNER JOIN STUDENT ON LECTURER.lecturer_id = STUDENT.scientific_director_id " +
            "INNER JOIN StudentArticleCrossRef ON STUDENT.student_id = StudentArticleCrossRef.student_id " +
            "INNER JOIN ARTICLE ON StudentArticleCrossRef.article_id = ARTICLE.article_id "
    )
    suspend fun getStrangeShit() : Map<Lecturer, List<StudentAndArticle>>

}