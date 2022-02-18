package com.example.roomexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.roomexamples.datasource.local.LocalDataSource
import com.example.roomexamples.utils.EmulatedData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var localDataSource: LocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch(Dispatchers.IO) {
            with(localDataSource) {
                insertData()
                printData()
                printCompositeData()
            }
        }
    }
}

suspend fun LocalDataSource.printCompositeData() {
//    Log.d("tag1C", "tag1 WWW DATA ${getStudentAndDiploma()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getLecturerAndStudent()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getStudentAndLecturer()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getStudentAndExam()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getLecturerAndExam()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getExamAndLecturer()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getStudentAndArticle()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getArticleAndStudent()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getLecturerStudentAndArticle()}")
//    Log.d("tag1C", "tag1 WWW DATA ${getLecturerArticles()}")
    Log.d("tag1C", "tag1 WWW DATA ${getStrangeShit()}")
}

suspend fun  LocalDataSource.printData() {
    Log.d("tag1", "tag1 WWW DATA ${getArticles()}")
    Log.d("tag1", "tag1 WWW DATA ${getStudents()}")
    Log.d("tag1", "tag1 WWW DATA ${getExams()}")
    Log.d("tag1", "tag1 WWW DATA ${getLecturers()}")
    Log.d("tag1", "tag1 WWW DATA ${getDiplomas()}")
}

suspend fun LocalDataSource.insertData() {
    insertLecturers(EmulatedData.getLecturers())
    insertStudents(EmulatedData.getStudents())
    insertArticles(EmulatedData.getArticles())
    insertStudentArticleCrossRef(EmulatedData.getStudentArticleCrossRef())
    insertDiplomas(EmulatedData.getDiplomas())
    insertExams(EmulatedData.getExams())

}