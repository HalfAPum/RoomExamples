package com.example.roomexamples.datasource.local

import com.example.roomexamples.datasource.local.dao.*
import com.example.roomexamples.datasource.local.entity.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val studentDao: StudentDao,
    private val articleDao: ArticleDao,
    private val examDao: ExamDao,
    private val lecturerDao: LecturerDao,
    private val diplomaDao: DiplomaDao,
    private val studentArticleCrossRefDao: StudentArticleCrossRefDao,
) {

    //Simple functions

    suspend fun insertStudents(students: Collection<Student>) = studentDao.insert(students)

    suspend fun insertArticles(articles: Collection<Article>) = articleDao.insert(articles)

    suspend fun insertExams(exams: Collection<Exam>) = examDao.insert(exams)

    suspend fun insertLecturers(lecturers: Collection<Lecturer>) = lecturerDao.insert(lecturers)

    suspend fun insertDiplomas(diplomas: Collection<Diploma>) = diplomaDao.insert(diplomas)

    suspend fun insertStudentArticleCrossRef(
        studentArticleCrossRef: Collection<StudentArticleCrossRef>
    ) = studentArticleCrossRefDao.insert(studentArticleCrossRef)

    suspend fun getStudents() = studentDao.getStudents()

    suspend fun getArticles() = articleDao.getArticles()

    suspend fun getExams() = examDao.getExams()

    suspend fun getLecturers() = lecturerDao.getLecturers()

    suspend fun getDiplomas() = diplomaDao.getDiploma()

    //Composite functions

    //One to one
    suspend fun getStudentAndDiploma() = studentDao.getStudentAndDiploma()

    //One to many
    suspend fun getLecturerAndStudent() = lecturerDao.getLecturerAndStudent()


    suspend fun getStudentAndExam() = studentDao.getStudentAndExam()

    suspend fun getLecturerAndExam() = lecturerDao.getLecturerAndExam()

    //Many to one(aka one to one)

    suspend fun getExamAndLecturer() = examDao.getExamAndLecturer()

    suspend fun getStudentAndLecturer() = studentDao.getStudentAndLecturer()

    //Many to many

    suspend fun getStudentAndArticle() = studentDao.getStudentAndArticle()

    suspend fun getArticleAndStudent() = articleDao.getArticleAndStudent()

    //Nested

    suspend fun getLecturerStudentAndArticle() = lecturerDao.getLecturerStudentAndArticle()

    //DatabaseView

    suspend fun getLecturerArticles() = lecturerDao.getLecturerArticles()

    suspend fun getStrangeShit() = lecturerDao.getStrangeShit()
}