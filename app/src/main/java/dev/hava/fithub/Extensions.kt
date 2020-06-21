package dev.hava.fithub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.startActivity(activity: Class<*>) {
    startActivity(Intent(this, activity))
}

fun Context.startActivityTop(activity: Class<*>) {
    startActivity(
        Intent(
            this,
            activity
        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    )
    if (this is Activity) {
        this.finish()
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}