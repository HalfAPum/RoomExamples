package com.example.roomexamples.utils

import com.example.roomexamples.datasource.local.entity.*

object EmulatedData {

    fun getStudents() = listOf(
        Student(10001,"IRTZI-18-2",
            PersonalData("IVAN", "GRYCNKO", "07-01-2001", "+3805066698723"),
            1,
        ),
        Student(10002,"IRTZI-18-2",
            PersonalData("ALEX", "SHEIKO", "33-11-2002", "+3803489753223"),
            1,
        ),
        Student(10003,"PZPI-19-3",
            PersonalData("Zhivaerv", "Artem", "12-05-1998", "+380556882368"),
            2,
        ),
        Student(10004,"PZPI-19-4",
            PersonalData("Maks", "Glushko", "07-01-2001", "+3805048672093"),
            3,
        ),
        Student(10005,"IRTZI-18-11",
            PersonalData("Sergey", "Narvatov", "01-01-2000", "+38672888743"),
            2,
        ),
    )

    fun getLecturers() = listOf(
        Lecturer(1, "Simple data professor", "5 years",
            PersonalData("Oleg", "Valishko", "07-01-1960", "+380437968972905"),
        ),
        Lecturer(2, "Big data real professor", "11 years",
            PersonalData("Vasil", "Pupko", "01-03-1950", "+382497628032"),
        ),
        Lecturer(3, "Algorithms and data structures expert", "7 years",
            PersonalData("Vindemal", "AEhghahk", "11-11-1965", "+380437298205"),
        ),
    )

    fun getExams() = listOf(
        Exam(studentId = 10001, subject = "Philisophy", examinatorId = 1, mark = 60),
        Exam(studentId = 10002, subject = "Data Structures", examinatorId = 2, mark = 40),
        Exam(studentId = 10003, subject = "Math", examinatorId = 3, mark = 70),
        Exam(studentId = 10004, subject = "Physics", examinatorId = 2, mark = 50),
        Exam(studentId = 10005, subject = "OOP", examinatorId = 3, mark = 80),
        Exam(studentId = 10001, subject = "Refactoring", examinatorId = 1, mark = 90),
        Exam(studentId = 10002, subject = "Algorithms", examinatorId = 1, mark = 90),
        Exam(studentId = 10003, subject = "Physics", examinatorId = 1, mark = 90),
        Exam(studentId = 10005, subject = "Refactoring", examinatorId = 2, mark = 66),
        Exam(studentId = 10004, subject = "Philisophy", examinatorId = 2, mark = 66),
    )

    fun getArticles() = listOf(
        Article(1, theme = "Some small theory about world",
            publishingHouse = "Moderna house", publicationDate = "07-09-2020"
        ),
        Article(2, theme = "Good news for all about COVID",
            publishingHouse = "Sputnik house", publicationDate = "08-11-2021"
        ),
        Article(3, theme = "Top grid inventions",
            publishingHouse = "Vide spread", publicationDate = "07-09-2020"
        ),
        Article(4, theme = "Violance against childrens",
            publishingHouse = "Moderna house", publicationDate = "07-09-2020"
        ),
        Article(5, theme = "Good boy is bad boy",
            publishingHouse = "Vide spread", publicationDate = "07-09-2020"
        ),
        Article(6, theme = "ted talks",
            publishingHouse = "Purple house", publicationDate = "07-09-2020"
        ),
        Article(7, theme = "Asian problems",
            publishingHouse = "Yellow house", publicationDate = "07-09-2020"
        ),
        Article(8, theme = "Russian problems",
            publishingHouse = "Red house", publicationDate = "07-09-2020"
        ),
        Article(9, theme = "Aliexpress fastest delivery",
            publishingHouse = "Green house", publicationDate = "07-09-2020"
        ),
        Article(10, theme = "No racists everywhere",
            publishingHouse = "BLACK house", publicationDate = "07-09-2020"
        ),
        Article(11, theme = "God bless america",
            publishingHouse = "WHITE house", publicationDate = "07-09-2020"
        ),
        Article(12, theme = "Some small theory about world",
            publishingHouse = "Moderna house", publicationDate = "07-09-2020"
        ),
        Article(13, theme = "Avoid shit",
            publishingHouse = "Sputnik house", publicationDate = "07-09-2020"
        ),
        Article(14, theme = "Krivuy rig is new international sport house?",
            publishingHouse = "Vasil house", publicationDate = "07-09-2020"
        ),
        Article(15, theme = "Top 10 rich farmers",
            publishingHouse = "Vasil house", publicationDate = "07-09-2020"
        ),
    )

    fun getDiplomas() = listOf(
        Diploma(studentId = 10001, theme = "Vechniu dvigatel liskova"),
        Diploma(studentId = 10002, theme = "Ptiza ubiiza"),
        Diploma(studentId = 10003, theme = "Super optiized sorting list"),
        Diploma(studentId = 10004, theme = "Some bullshit"),
        Diploma(studentId = 10005, theme = "Daite troechky pliz"),
    )

    fun getStudentArticleCrossRef() = listOf(
        StudentArticleCrossRef(10001, 1),
        StudentArticleCrossRef(10001, 2),
        StudentArticleCrossRef(10001, 3),
        StudentArticleCrossRef(10001, 4),
        StudentArticleCrossRef(10002, 5),
        StudentArticleCrossRef(10002, 6),
        StudentArticleCrossRef(10003, 7),
        StudentArticleCrossRef(10001, 8),
        StudentArticleCrossRef(10004, 9),
        StudentArticleCrossRef(10004, 10),
        StudentArticleCrossRef(10005, 11),
        StudentArticleCrossRef(10001, 12),
        StudentArticleCrossRef(10004, 13),
        StudentArticleCrossRef(10001, 14),
        StudentArticleCrossRef(10004, 15),
        StudentArticleCrossRef(10004, 1),
        StudentArticleCrossRef(10004, 2),
        StudentArticleCrossRef(10004, 3),
        StudentArticleCrossRef(10004, 4),
        StudentArticleCrossRef(10001, 5),
        StudentArticleCrossRef(10004, 12),
        StudentArticleCrossRef(10001, 7),
        StudentArticleCrossRef(10005, 3),
        StudentArticleCrossRef(10005, 1),
        StudentArticleCrossRef(10005, 15),
        StudentArticleCrossRef(10005, 14),
        StudentArticleCrossRef(10005, 7),
        StudentArticleCrossRef(10003, 1),
        StudentArticleCrossRef(10005, 1),
    )
}