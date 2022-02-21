package com.example.roomexamples.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomexamples.datasource.local.dao.base.BaseDaoTest
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.utils.EmulatedData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest : BaseDaoTest<ArticleDao, Article>() {

    override val singleItem = EmulatedData.getSingleArticle()
    override val itemList = EmulatedData.getArticles()

    //Dao for composite queries
    private lateinit var lecturerDao: LecturerDao
    private lateinit var studentDao: StudentDao
    private lateinit var studentArticleCrossRefDao: StudentArticleCrossRefDao

    @Before
    override fun setup() {
        super.setup()
        dao = db.articleDao()
        lecturerDao = db.lecturerDao()
        studentDao = db.studentDao()
        studentArticleCrossRefDao = db.studentArticleCrossRefDao()
    }

    override suspend fun getItemsFromDao() = dao.getArticles()

    @Test
    fun getArticleAndStudents() = runTest {
        fillFullDbWithEmulatedData()

        val articles = EmulatedData.getArticles()

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