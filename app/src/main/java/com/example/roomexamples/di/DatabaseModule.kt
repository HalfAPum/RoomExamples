package com.example.roomexamples.di

import android.content.Context
import androidx.room.Room
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

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext applicationContext: Context
    ) : AppDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "student-db"
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