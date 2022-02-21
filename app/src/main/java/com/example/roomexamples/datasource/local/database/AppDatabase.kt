package com.example.roomexamples.datasource.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
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
    autoMigrations = [
        AutoMigration(from = 13, to = 14),
        AutoMigration(
            from = 15,
            to = 16,
            spec = AppDatabase.DeleteAdditionalDescriptionMigration::class
        ),
     ],
    version = 18,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    @DeleteColumn(tableName = "Diploma", columnName = "description_additional")
    class DeleteAdditionalDescriptionMigration : AutoMigrationSpec

    abstract fun studentDao() : StudentDao

    abstract fun articleDao() : ArticleDao

    abstract fun examDao() : ExamDao

    abstract fun lecturerDao() : LecturerDao

    abstract fun diplomaDao() : DiplomaDao

    abstract fun studentArticleCrossRefDao() : StudentArticleCrossRefDao
}