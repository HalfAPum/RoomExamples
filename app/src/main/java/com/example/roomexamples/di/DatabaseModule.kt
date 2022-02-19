package com.example.roomexamples.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomexamples.datasource.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    val MIGRATION_14_15 = object : Migration(14, 15) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Diploma ADD COLUMN description_additional TEXT")
        }
    }

    val MIGRATION_16_17 = object : Migration(16, 17) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE INDEX index_exam_examinator_id ON exam(examinator_id)")
        }
    }

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext applicationContext: Context
    ) : AppDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "student-db"
    ).addMigrations(
        MIGRATION_14_15,
        MIGRATION_16_17,
    ).build()

    @Singleton
    @Provides
    fun provideStudentDao(appDatabase: AppDatabase) = appDatabase.studentDao()

    @Singleton
    @Provides
    fun provideLecturerDao(appDatabase: AppDatabase) = appDatabase.lecturerDao()

    @Singleton
    @Provides
    fun provideExamDao(appDatabase: AppDatabase) = appDatabase.examDao()

    @Singleton
    @Provides
    fun provideArticleDao(appDatabase: AppDatabase) = appDatabase.articleDao()

    @Singleton
    @Provides
    fun provideDiplomaDao(appDatabase: AppDatabase) = appDatabase.diplomaDao()

    @Singleton
    @Provides
    fun provideStudentArticleCrossRefDao(appDatabase: AppDatabase) = appDatabase.studentArticleCrossRefDao()

}