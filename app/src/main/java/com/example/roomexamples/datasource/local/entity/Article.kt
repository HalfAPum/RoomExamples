package com.example.roomexamples.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    val articleId: Int = 0,
    @ColumnInfo(name = "theme")
    val theme: String?,
    @ColumnInfo(name = "publishing_house")
    val publishingHouse: String?,
    @ColumnInfo(name = "publication_date")
    val publicationDate: String?,
)