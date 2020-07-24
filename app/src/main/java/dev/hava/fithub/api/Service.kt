package dev.hava.fithub.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Service {
    @POST("sign_up")
    fun signUp(
        @Body signUp: SignUp
    ): Call<Auth>

    @POST("login")
    fun login(
        @Body login: Login
    ): Call<Auth>

    @POST("insert_history")
    fun insertHistory(
        @Body history: History
    ): Call<HistoryRes>

    @POST("get_history")
    fun getHistory(
        @Body user: User
    ): Call<HistoriesRes>

    @POST("add_course")
    fun addCourse(
        @Body course: Course
    ): Call<CourseRes>

    @POST("enroll_course")
    fun enrollCourse(
        @Body enrollment: Enrollment
    ): Call<EnrollmentRes>

    @POST("left_course")
    fun leftCourse(
        @Body leave: Leave
    ): Call<Status>

    @POST("add_post")
    fun addPost(
        @Body post: Post
    ): Call<PostRes>

    @POST("edit_post")
    fun editPost(
        @Body history: PostEdit
    ): Call<PostRes>

    @POST("delete_post")
    fun deletePost(
        @Body history: PostDelete
    ): Call<Status>

    @POST("get_course_posts")
    fun getPosts(
        @Body posts: Posts
    ): Call<PostsRes>

    @POST("get_courses")
    fun getCourses(
    ): Call<CoursesRes>

    @POST("get_my_courses")
    fun getMyCourses(
        @Body user: User
    ): Call<CoursesRes>


}