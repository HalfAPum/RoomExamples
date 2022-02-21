package com.example.roomexamples.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.roomexamples.datasource.local.entity.StudentArticleCrossRef

@Dao
interface StudentArticleCrossRefDao : BaseDao<StudentArticleCrossRef> {

    @Query("SELECT student_id FROM StudentArticleCrossRef WHERE article_id = :articleId")
    fun getStudentIdsByArticleId(articleId: Int): List<Int>

    @Query("SELECT article_id FROM StudentArticleCrossRef WHERE student_id = :studentId")
    fun getArticleIdsByStudentId(studentId: Int): List<Int>

    @Query("SELECT * FROM StudentArticleCrossRef")
    fun getStudentArticleCrossRef(): List<StudentArticleCrossRef>

}