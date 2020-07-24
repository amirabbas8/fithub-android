package dev.hava.fithub.api

import com.google.gson.annotations.Expose

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

class Login(
    @Expose
    val username: String,
    @Expose
    val password: String
)

class User(
    @Expose
    val user_id: Int
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


class Enrollment(
    @Expose
    val user_id: Int,
    @Expose
    val course_id: Int
)

class Leave(
    @Expose
    val course_student_id: Int
)

class Post(
    @Expose
    val course_id: Int,
    @Expose
    val text: String,
    @Expose
    val order: Int
)

class PostEdit(
    @Expose
    val post_id: Int,
    @Expose
    val text: String,
    @Expose
    val order: Int
)


class PostDelete(
    @Expose
    val post_id: Int
)


class Posts(
    @Expose
    val course_id: Int
)