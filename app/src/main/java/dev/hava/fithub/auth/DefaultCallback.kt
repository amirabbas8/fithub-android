package dev.hava.fithub.auth

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class DefaultCallback<T>(
    private val context: Context,
    val callback: ((Response<T>) -> Unit)? = null,
    val failCallback: (() -> Unit)? = null
) :
    retrofit2.Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        t.printStackTrace()
        failCallback?.invoke()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            callback?.invoke(response)
        } else {
            if (response.code() == 400) {
                //error
            } else {
                failCallback?.invoke()
            }
        }
    }
}