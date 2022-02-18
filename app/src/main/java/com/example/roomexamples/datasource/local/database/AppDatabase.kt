package com.example.roomexamples.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomexamples.datasource.local.dao.*
import com.example.roomexamples.datasource.local.entity.*
import com.example.roomexamples.datasource.local.view.LecturerArticles

@Database(
    entities = [
        Article::class,
        Exam::class,
        Lecturer::class,
        Student::class,
        Diploma::class,
        StudentArticleCrossRef::class,
    ],
    views = [
        LecturerArticles::class
    ],
    version = 12,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao() : StudentDao

    abstract fun articleDao() : ArticleDao

    abstract fun examDao() : ExamDao

    abstract fun lecturerDao() : LecturerDao

    abstract fun diplomaDao() : DiplomaDao

    abstract fun studentArticleCrossRefDao() : StudentArticleCrossRefDao
}