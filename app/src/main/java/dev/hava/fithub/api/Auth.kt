package dev.hava.fithub.api

import android.content.Context
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Auth(
    @SerializedName("status")
    @Expose
    private val status: String,
    @SerializedName("user_id")
    @Expose
    val user_id: Int?
) {
    fun save(context: Context) {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE).edit()
        prefs.putInt("user_id", user_id ?: 0)
        prefs.apply()
    }

    companion object {
        fun load(context: Context): Auth? {
            val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            return Auth(
                "",
                prefs.getInt("user_id", 0)
            )
        }

        fun signUp(
            username: String,
            password: String,
            email: String,
            phone: String,
            licenseNumber: String,
            defaultCallback: DefaultCallback<Auth>
        ) {
            val service = Instance.instance()
            service.signUp(Service.SignUp(username, password, email, phone, licenseNumber))
                .enqueue(defaultCallback)
        }

        fun login(
            username: String,
            password: String,
            defaultCallback: DefaultCallback<Auth>
        ) {
            val service = Instance.instance()
            service.login(Service.Login(username, password))
                .enqueue(defaultCallback)
        }
    }
}