package dev.hava.fithub.api

import com.google.gson.JsonArray
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Status(
    @SerializedName("status")
    @Expose
    val status: String
)

class HistoryRes(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("student_history")
    @Expose
    val student_history: Int?
)

class HistoriesRes(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("histories")
    @Expose
    val histories: JsonArray?
)

class CourseRes(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("course_id")
    @Expose
    val course_id: Int?
)

class EnrollmentRes(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("student_history")
    @Expose
    val course_student_id: Int?
)

class PostRes(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("post_id")
    @Expose
    val post_id: Int?
)

class PostsRes(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("posts")
    @Expose
    val posts: JsonArray?
)

class CoursesRes(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("courses")
    @Expose
    val courses: JsonArray?
)