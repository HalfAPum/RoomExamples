package com.example.roomexamples.datasource.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomexamples.datasource.local.database.AppDatabase
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.utils.EmulatedData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var dao: ArticleDao
    //For composite queries
    private lateinit var lecturerDao: LecturerDao
    private lateinit var studentDao: StudentDao
    private lateinit var studentArticleCrossRefDao: StudentArticleCrossRefDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()

        dao = db.articleDao()
        lecturerDao = db.lecturerDao()
        studentDao = db.studentDao()
        studentArticleCrossRefDao = db.studentArticleCrossRefDao()
    }

    @After
    fun teardown() = db.close()

    @Test
    fun insertSingleArticle() = runBlockingTest {
        val singleArticle = Article(
            articleId = 1,
            theme = "Saving people from themeselves",
            publishingHouse = "New house of moderna",
            publicationDate = "06-06-2002",
        )
        dao.insert(singleArticle)

        val resultArticles = dao.getArticles()

        assertThat(resultArticles).contains(singleArticle)
    }

    @Test
    fun insertArticlesList() = runBlockingTest {
        val articles = EmulatedData.getArticles()
        dao.insert(articles)

        val resultArticles = dao.getArticles()

        assertThat(resultArticles).isEqualTo(articles)
    }

    @Test
    fun deleteArticle() = runBlockingTest {
        val articles = EmulatedData.getArticles()
        dao.insert(articles)

        val articleToDelete = articles.random()
        dao.delete(articleToDelete)

        val resultArticles = dao.getArticles()

        assertThat(resultArticles).doesNotContain(articleToDelete)
    }

    @Test
    fun deleteArticles() = runBlockingTest {
        val articles = EmulatedData.getArticles()
        dao.insert(articles)

        val articlesToDelete = listOf(articles.first(), articles.last())
        dao.delete(articlesToDelete)

        val resultArticles = dao.getArticles()

        assertThat(resultArticles).doesNotContain(articlesToDelete)
    }

    @Test
    fun getArticleAndStudents() = runBlockingTest {
        val lecturers = EmulatedData.getLecturers()
        val students = EmulatedData.getStudents()
        val articles = EmulatedData.getArticles()
        val studentArticleCrossRefs = EmulatedData.getStudentArticleCrossRef()

        lecturerDao.insert(lecturers)
        studentDao.insert(students)
        dao.insert(articles)
        studentArticleCrossRefDao.insert(studentArticleCrossRefs)

        val idsList = mutableListOf<Pair<Int, List<Int>>>()
        for (article in articles) {
            val studentIdsByArticle = studentArticleCrossRefDao
                .getStudentIdsByArticleId(article.articleId)

            idsList.add(Pair(article.articleId, studentIdsByArticle))
        }

        val resultIdsList = dao.getArticleAndStudent().map {
            Pair(it.article.articleId, it.student.map { it.studentId })
        }

        assertThat(resultIdsList).isEqualTo(idsList)
    }

}