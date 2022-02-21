package com.example.roomexamples.datasource.local.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomexamples.datasource.local.dao.base.BaseDaoTest
import com.example.roomexamples.datasource.local.entity.StudentArticleCrossRef
import com.example.roomexamples.utils.EmulatedData
import com.google.common.truth.Truth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class StudentArticleCrossRefDaoTest: BaseDaoTest<StudentArticleCrossRefDao, StudentArticleCrossRef>() {

    override val singleItem = EmulatedData.getSingleStudentArticleCrossRef()
    override val itemList = EmulatedData.getStudentArticleCrossRef()

    override suspend fun getItemsFromDao() = dao.getStudentArticleCrossRef()

    @Before
    override fun setup() {
        super.setup()
        dao = db.studentArticleCrossRefDao()
        fillData()
    }

    private fun fillData() = runTest {
        db.lecturerDao().insert(EmulatedData.getLecturers())
        db.studentDao().insert(EmulatedData.getStudents())
        db.articleDao().insert(EmulatedData.getArticles())
    }

    private fun fillWithStudentArticleCrossRef() = runTest {
        db.studentArticleCrossRefDao().insert(itemList)
    }

    @Test
    fun getStudentIdsByArticleId() = runTest {
        fillWithStudentArticleCrossRef()
        val articleId = itemList.random().articleId

        val studentIds = itemList.filter { it.articleId == articleId }.map { it.studentId }.toMutableList().sort()

        val resultStudentIds = dao.getStudentIdsByArticleId(articleId).toMutableList().sort()

        assertThat(resultStudentIds).isEqualTo(studentIds)
    }

    @Test
    fun getArticleIdsByStudentId() = runTest {
        fillWithStudentArticleCrossRef()
        val studentId = itemList.random().studentId

        val articleIds = itemList.filter { it.studentId == studentId }.map { it.articleId }.toMutableList().sort()

        val resultArticleIds = dao.getArticleIdsByStudentId(studentId).toMutableList().sort()

        //TODO think about extension is equal to that sort two lists
        // so there no situation when
        // exception contents are identical but in wrong order appear
        assertThat(resultArticleIds).isEqualTo(articleIds)
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertNotExistingStudentId() = runTest {
        val notExistingStudentId = -10
        val existingArticleId = 1
        dao.insert(
            StudentArticleCrossRef(
                notExistingStudentId,
                existingArticleId,
            )
        )
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertNotExistingArticleId() = runTest {
        val existingStudentId = 10001
        val nonExistingArticleId = -10
        dao.insert(
            StudentArticleCrossRef(
                existingStudentId,
                nonExistingArticleId,
            )
        )
    }

}