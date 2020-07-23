package dev.hava.fithub.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Service {
    @POST("sign_up")
    fun signUp(
        @Body signUp: SignUp
    ): Call<Auth>

    class SignUp(
        @Expose
        val username: String,
        @Expose
        val password: String,
        @Expose
        val email: String,
        @Expose
        val phone: String,
        @Expose
        val license_number: String
    )

    @POST("login")
    fun login(
        @Body login: Login
    ): Call<Auth>

    class Login(
        @Expose
        val username: String,
        @Expose
        val password: String
    )

    @POST("insert_history")
    fun insertHistory(
        @Body history: History
    ): Call<HistoryRes>

    class Status(
        @SerializedName("status")
        @Expose
        val status: String
    )

    class History(
        @Expose
        val user_id: Int,
        @Expose
        val history_type: Int,
        @Expose
        val details: String,
        @Expose
        val value: String
    )

    class HistoryRes(
        @SerializedName("status")
        @Expose
        val status: String,
        @SerializedName("student_history")
        @Expose
        val student_history: Int?
    )

    @POST("get_history")
    fun getHistory(
        @Body user: User
    ): Call<HistoriesRes>

    class User(
        @Expose
        val user_id: Int
    )

    class HistoriesRes(
        @SerializedName("status")
        @Expose
        val status: String,
        @SerializedName("histories")
        @Expose
        val histories: Int?
    )

    @POST("add_course")
    fun addCourse(
        @Body course: Course
    ): Call<CourseRes>

    class Course(
        @Expose
        val user_id: Int,
        @Expose
        val name: String,
        @Expose
        val price: Int,
        @Expose
        val value: String
    )

    class CourseRes(
        @SerializedName("status")
        @Expose
        val status: String,
        @SerializedName("course_id")
        @Expose
        val course_id: Int?
    )

    @POST("enroll_course")
    fun enrollCourse(
        @Body enrollment: Enrollment
    ): Call<EnrollmentRes>

    class Enrollment(
        @Expose
        val user_id: Int,
        @Expose
        val course_id: Int
    )

    class EnrollmentRes(
        @SerializedName("status")
        @Expose
        val status: String,
        @SerializedName("student_history")
        @Expose
        val course_student_id: Int?
    )

    @POST("left_course")
    fun leftCourse(
        @Body leave: Leave
    ): Call<Status>

    class Leave(
        @Expose
        val course_student_id: Int
    )

    @POST("Post")
    fun addPost(
        @Body post: Post
    ): Call<PostRes>

    class Post(
        @Expose
        val course_id: Int,
        @Expose
        val text: String,
        @Expose
        val order: Int
    )

    class PostRes(
        @SerializedName("status")
        @Expose
        val status: String,
        @SerializedName("post_id")
        @Expose
        val post_id: Int?
    )


    @POST("editPost")
    fun editPost(
        @Body history: PostEdit
    ): Call<PostRes>

    class PostEdit(
        @Expose
        val post_id: Int,
        @Expose
        val text: String,
        @Expose
        val order: Int
    )

    @POST("delete_post")
    fun deletePost(
        @Body history: PostDelete
    ): Call<Status>

    class PostDelete(
        @Expose
        val post_id: Int
    )

    @POST("get_courses")
    fun getCourses(
    ): Call<CoursesRes>


    @POST("get_my_courses")
    fun getMyCourses(
        @Body user: User
    ): Call<CoursesRes>


    class CoursesRes(
        @SerializedName("status")
        @Expose
        val status: String,
        @SerializedName("courses")
        @Expose
        val courses: Int?
    )

}