package dev.hava.fithub.api


import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory


object Instance {
    private val httpClient = OkHttpClient.Builder()
    private var retrofit: Service? = null
    private const val BASE_URL = "http://10.0.3.2:8000/"

    fun instance(): Service {
        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder().client(httpClient.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service::class.java)
        }
        return retrofit!!
    }

    fun getUserId(context: Context): Int? {
        return Auth.load(context)?.user_id
    }

    fun insertHistory(
        context: Context,
        type: Int,
        details: String,
        value: String,
        defaultCallback: DefaultCallback<Auth>
    ) {
        getUserId(context)?.let {
            instance().insertHistory(Service.History(it, type, details, value))
                .enqueue(defaultCallback)
        }
    }

}
