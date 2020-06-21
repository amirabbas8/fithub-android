package dev.hava.fithub.api

import com.google.gson.annotations.Expose
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
    ): Call<Auth>

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
}