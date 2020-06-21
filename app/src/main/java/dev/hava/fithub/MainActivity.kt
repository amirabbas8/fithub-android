package dev.hava.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.hava.fithub.api.Instance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Instance.getUserId(this) ?: 0 == 0) {
            startActivityTop(LoginActivity::class.java)
        }
        setContentView(R.layout.activity_main)

//todo        RetrofitInstance.insertHistory(this, 1, "s", "5", DefaultCallback(this))
    }
}