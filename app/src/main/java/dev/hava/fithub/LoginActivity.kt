package dev.hava.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.content.Context
import dev.hava.fithub.auth.Auth
import dev.hava.fithub.auth.DefaultCallback
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var isSignUp = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        change.setOnClickListener {
            isSignUp = !isSignUp
            change.setText(if (isSignUp) R.string.login else R.string.register)
            ok.setText(if (isSignUp) R.string.sign_up else R.string.sign_in)
            if (isSignUp) phone.visible() else phone.gone()
            if (isSignUp) email.visible() else email.gone()
            if (isSignUp) license.visible() else license.gone()
        }
        ok.setOnClickListener {
            loading.visible()
            if (!isSignUp) {
                login()
            } else {
                signUp()
            }

        }
    }

    private val defaultCallback = DefaultCallback<Auth>(this, {
        if (it.isSuccessful) {
            it.body()?.save(this)
            startActivityTop(MainActivity::class.java)
        }
    }, {
        loading.gone()
        toast("ERROR")
    })

    private fun login() {
        Auth.login(
            username.text.toString(), password.text.toString(),
            defaultCallback
        )
    }

    private fun error() {
        loading.gone()
        toast("ERROR")
    }

    private fun signUp() {
        Auth.signUp(
            username.text.toString(),
            password.text.toString(),
            email.text.toString(),
            phone.text.toString(),
            license.text.toString(),
            defaultCallback
        )
    }
}