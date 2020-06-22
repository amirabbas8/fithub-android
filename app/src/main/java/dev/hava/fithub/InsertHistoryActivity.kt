package dev.hava.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.hava.fithub.api.DefaultCallback
import dev.hava.fithub.api.Instance
import kotlinx.android.synthetic.main.activity_insert_history.*

class InsertHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_history)
        ok.setOnClickListener {
            Instance.insertHistory(
                this,
                type.selectedItemId.toInt(),
                details.text.toString(),
                value.text.toString(),
                DefaultCallback(this, {
                    toast("با موفقیت انجام شد.")
                    finish()
                }, {
                    toast("خطایی رخ داد.")
                })
            )
        }
    }
}