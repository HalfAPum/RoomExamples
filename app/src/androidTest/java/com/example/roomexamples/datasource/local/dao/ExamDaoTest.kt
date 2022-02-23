package com.example.roomexamples.datasource.local.dao

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomexamples.datasource.local.dao.base.BaseDaoTest
import com.example.roomexamples.datasource.local.entity.Exam
import com.example.roomexamples.datasource.local.entity.composite.manytoome.ExamAndExaminator
import com.example.roomexamples.utils.EmulatedData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class ExamDaoTest : BaseDaoTest<ExamDao, Exam>() {

    override val singleItem = EmulatedData.getSingleExam()
    override val itemList = EmulatedData.getExams()

    override suspend fun getItemsFromDao() = dao.getExams()

    @Before
    override fun setup() {
        super.setup()
        dao = db.examDao()
        fillLecturerAndStudent()
    }

    private fun fillLecturerAndStudent() = runTest {
        db.lecturerDao().insert(EmulatedData.getLecturers())
        db.studentDao().insert(EmulatedData.getStudents())
    }

    @Test
    fun getExamAndLecturer() = runTest {
        fillFullDbWithEmulatedData()
        val lecturers = EmulatedData.getLecturers()
        val exams = EmulatedData.getExams()

        val emulatedData = mutableListOf<ExamAndExaminator>()
        for(exam in exams) {
            val examinator = lecturers.find { it.lecturerId == exam.examinatorId }
            examinator?.let { emulatedData.add(ExamAndExaminator(exam, it)) }
        }

        val resultData = dao.getExamAndLecturer()

        assertThat(resultData).isEqualTo(emulatedData)
    }

    //Test Flow

    @Test
    fun getEmptyFlow() = runTest {
        val result = dao.getExamsContinuous().first()
        assertThat(result).isEmpty()
    }

    @Test
    fun getFlowWithData() = runTest {
        val exams = EmulatedData.getExams()
        dao.insert(exams)
        val result = dao.getExamsContinuous().first()
        assertThat(result).isEqualTo(exams)
    }

    @Test
    fun getFlowWithDataUpdatedTwice() = runTest {
        val exams = EmulatedData.getExams()
        val exams1 = exams.subList(0,3)
        val exams2 = exams.subList(3,6)
        dao.insert(exams1)
        dao.insert(exams2)
        val result = dao.getExamsContinuous().first()
        assertThat(result).isEqualTo(exams1 + exams2)
    }

    @Test
    fun testFlowAgain() = runTest {
        val exams = EmulatedData.getExams()
        val exams1 = exams.subList(0,3)
        val exams2 = exams.subList(3,6)

        val deferred = async { dao.getExamsContinuous().take(2).toList() }

        dao.insert(exams1)
        dao.insert(exams2)

        val result = deferred.await()

        Log.d("tag1", "tag1 WTF ${result}")

        assertThat(result).isEqualTo(listOf(exams1 + exams2, exams1))
    }

}