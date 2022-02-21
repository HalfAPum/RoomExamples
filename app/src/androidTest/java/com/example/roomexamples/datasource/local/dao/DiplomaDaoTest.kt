package com.example.roomexamples.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomexamples.datasource.local.dao.base.BaseDaoTest
import com.example.roomexamples.datasource.local.entity.Diploma
import com.example.roomexamples.utils.EmulatedData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class DiplomaDaoTest: BaseDaoTest<DiplomaDao, Diploma>() {

    override val singleItem = EmulatedData.getSingleDiploma()
    override val itemList = EmulatedData.getDiplomas()

    @Before
    override fun setup() {
        super.setup()
        dao = db.diplomaDao()
        fillLecturerAndStudent()
    }

    private fun fillLecturerAndStudent() = runTest {
        db.lecturerDao().insert(EmulatedData.getLecturers())
        db.studentDao().insert(EmulatedData.getStudents())
    }

    override suspend fun getItemsFromDao() = dao.getDiploma()



}