package dev.hava.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.hava.fithub.api.Instance
import kotlinx.android.synthetic.main.activity_main.*

class AddPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
    }
}