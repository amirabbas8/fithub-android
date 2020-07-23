package dev.hava.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.hava.fithub.api.Instance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Instance.getUserId(this) ?: 0 == 0) {
            startActivityTop(LoginActivity::class.java)
        }
        setContentView(R.layout.activity_main)
        insertHistory.setOnClickListener {
            startActivity(InsertHistoryActivity::class.java)
        }
    }
}