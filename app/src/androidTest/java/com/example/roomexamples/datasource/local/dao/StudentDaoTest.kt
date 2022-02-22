package com.example.roomexamples.datasource.local.dao

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomexamples.datasource.local.dao.base.BaseDaoTest
import com.example.roomexamples.datasource.local.entity.Article
import com.example.roomexamples.datasource.local.entity.Exam
import com.example.roomexamples.datasource.local.entity.Student
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
class StudentDaoTest : BaseDaoTest<StudentDao, Student>() {

    override val singleItem = EmulatedData.getSingleStudent()
    override val itemList = EmulatedData.getStudents()

    override suspend fun getItemsFromDao() = dao.getStudents()

    @Before
    override fun setup() {
        super.setup()
        dao = db.studentDao()
        fillDb()
    }

    private fun fillDb() = runTest {
        db.lecturerDao().insert(EmulatedData.getLecturers())
    }

    private fun fillDbForManyToManyRelation() = runTest {
        db.studentDao().insert(EmulatedData.getStudents())
        db.articleDao().insert(EmulatedData.getArticles())
        db.studentArticleCrossRefDao().insert(EmulatedData.getSingleStudentArticleCrossRef())
    }

    private fun fillExams() = runTest {
        db.examDao().insert(EmulatedData.getExams())
    }

    private fun fillDiplomas() = runTest {
        db.diplomaDao().insert(EmulatedData.getDiplomas())
    }

    private fun fillStudents() = runTest {
        dao.insert(itemList)
    }

    @Test
    fun getStudentDiploma() = runTest {
        fillStudents()
        fillDiplomas()
        val diplomas = EmulatedData.getDiplomas().map { it.studentId }

        val resultStudentIds = dao.getStudentAndDiploma().map { it.student.studentId }

        assertThat(resultStudentIds).isEqualTo(diplomas)
    }

    @Test(expected = SQLiteConstraintException::class)
    fun insertStudentWithScientificDirectorId() = runTest {
        db.studentDao().insert(EmulatedData.getSingleStudentWithFakeScientificDirectorId())
    }

    @Test
    fun getStudentAndScientificDirector() = runTest {
        fillStudents()
        val lecturerIds = EmulatedData.getLecturers().map { it.lecturerId }.toSet()

        val resultScientificDirectorIds = dao.getStudentAndLecturer().map { it.lecturer.lecturerId }.toSet()

        assertThat(resultScientificDirectorIds).isEqualTo(lecturerIds)
    }

    @Test
    fun getStudentAndExam() = runTest {
        fillStudents()
        fillExams()
        val exams = EmulatedData.getExams()
        val students = EmulatedData.getStudents()

        val examIds = mutableListOf<Exam>()
        for (student in students) {
            for(exam in exams) {
                if (exam.studentId == student.studentId) examIds.add(exam)
            }
        }

        val resultExamIds = dao.getStudentAndExam().map { it.exams }
            .flatten().toMutableList()

        resultExamIds.sortBy { it.examId }
        examIds.sortBy { it.examId }

        assertThat(resultExamIds).isEqualTo(examIds)
    }

}