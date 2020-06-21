package dev.hava.fithub.auth


import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val httpClient = OkHttpClient.Builder()
    private var retrofit: retrofit2.Retrofit? = null

    //    private var authRetrofit: retrofit2.Retrofit? = null
    private const val BASE_URL = "http://10.0.3.2:8000/"

    fun instance(): retrofit2.Retrofit {
        if (retrofit == null) {

//            val interceptor = AuthenticationInterceptor(authToken)
//            httpClient.addInterceptor(interceptor)
            retrofit = retrofit2.Retrofit.Builder().client(httpClient.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

}
