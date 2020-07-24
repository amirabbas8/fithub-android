package dev.hava.fithub

class HistoryModel(
    val historyId: Int,
    val historyType: Int,
    val details: String,
    val value: String
)

class CourseModel(
    val courseId: Int,
    val name: String,
    val price: Int
)

class PostModel(
    val postId: Int,
    val text: String,
    val order: Int
)
