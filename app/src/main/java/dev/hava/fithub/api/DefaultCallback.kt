package dev.hava.fithub.api

import android.content.Context
import retrofit2.Call
import retrofit2.Response

class DefaultCallback<T>(
    private val context: Context,
    private val callback: ((Response<T>) -> Unit)? = null,
    private val failCallback: (() -> Unit)? = null
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