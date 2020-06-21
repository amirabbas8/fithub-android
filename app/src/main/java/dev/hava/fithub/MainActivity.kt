package dev.hava.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.hava.fithub.auth.Auth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = Auth.load(this)
        if (auth?.user_id ?: 0 == 0) {
            startActivityTop(LoginActivity::class.java)
        }
        setContentView(R.layout.activity_main)
    }
}