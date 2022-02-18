package com.example.roomexamples.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.datasource.local.entity.Student
import com.example.roomexamples.datasource.local.entity.composite.manytomany.ArticleAndStudent
import com.example.roomexamples.datasource.local.entity.composite.manytomany.StudentAndArticle

@Dao
interface ArticleDao : BaseDao<Article> {

    @Query("SELECT * FROM Article")
    suspend fun getArticles() : List<Article>

    //Composite functions

    @Transaction
    @Query("SELECT * FROM Article")
    suspend fun getArticleAndStudent() : List<ArticleAndStudent>

}