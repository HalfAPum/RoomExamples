package com.example.roomexamples.datasource.local.dao.base

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.roomexamples.datasource.local.dao.BaseDao
import com.example.roomexamples.datasource.local.database.AppDatabase
import com.example.roomexamples.utils.EmulatedData
import com.google.common.truth.Truth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * This class make testing easier for Room's Dao.
 * It contains Base Tests for all dao's that extends BaseDao
 * so you don't need write boilerplate code for simple methods
 * like Insert, Delete, Update, and Simple getAll
 */
@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseDaoTest<T : BaseDao<R>, R> {


    protected abstract val singleItem: R
    protected abstract val itemList: List<R>

    protected lateinit var db: AppDatabase
    protected lateinit var dao: T

    @Before
    @CallSuper
    open fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()
    }

    /**
     * Fill full db to avoid constraint fails
     */
    protected fun fillFullDbWithEmulatedData() = runTest {
        db.lecturerDao().insert(EmulatedData.getLecturers())
        db.studentDao().insert(EmulatedData.getStudents())
        db.diplomaDao().insert(EmulatedData.getDiplomas())
        db.examDao().insert(EmulatedData.getExams())
        db.articleDao().insert(EmulatedData.getArticles())
        db.studentArticleCrossRefDao().insert(EmulatedData.getStudentArticleCrossRef())
    }

    @After
    fun teardown() = db.close()

    abstract suspend fun getItemsFromDao() : List<R>

    @Test
    fun insertSingleItem() = runTest {
        dao.insert(singleItem)

        val resultArticles = getItemsFromDao()

        assertThat(resultArticles).contains(singleItem)
    }

    @Test
    fun insertItemList() = runTest {
        dao.insert(itemList)

        val resultArticles = getItemsFromDao()

        assertThat(resultArticles).isEqualTo(itemList)
    }

    @Test
    fun deleteSingleItem() = runTest {
        dao.insert(itemList)

        val articleToDelete = itemList.random()
        dao.delete(articleToDelete)

        val resultArticles = getItemsFromDao()

        assertThat(resultArticles).doesNotContain(articleToDelete)
    }

    @Test
    fun deleteItemList() = runTest {
        dao.insert(itemList)

        val itemListCopy = itemList.toMutableList()
        val itemListToDelete = mutableListOf<R>()

        //Delete random half of the list using random()
        for(i in (0..itemList.size/2)) {
            val itemToDelete = itemListCopy.random()
            itemListToDelete.add(itemToDelete)
            itemListCopy.remove(itemToDelete)
        }

        dao.delete(itemListToDelete)

        val resultArticles = getItemsFromDao()

        assertThat(resultArticles).doesNotContain(itemListToDelete)
    }

}