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
        defaultCallback: DefaultCallback<Service.HistoryRes>
    ) {
        getUserId(context)?.let {
            instance().insertHistory(Service.History(it, type, details, value))
                .enqueue(defaultCallback)
        }
    }

    fun addCourse(
        context: Context,
        name: String,
        price: Int,
        value: String,
        defaultCallback: DefaultCallback<Service.CourseRes>
    ) {
        getUserId(context)?.let {
            instance().addCourse(Service.Course(it, name, price, value)).enqueue(defaultCallback)
        }
    }

    fun enrollCourse(
        context: Context,
        courseId: Int,
        defaultCallback: DefaultCallback<Service.EnrollmentRes>
    ) {
        getUserId(context)?.let {
            instance().enrollCourse(Service.Enrollment(it, courseId)).enqueue(defaultCallback)
        }
    }

    fun leftCourse(
        context: Context,
        courseStudentId: Int,
        defaultCallback: DefaultCallback<Service.Status>
    ) {
        getUserId(context)?.let {
            instance().leftCourse(Service.Leave(courseStudentId)).enqueue(defaultCallback)
        }
    }

    fun addPost(
        context: Context,
        courseId: Int,
        text: String,
        order: Int,
        defaultCallback: DefaultCallback<Service.PostRes>
    ) {
        getUserId(context)?.let {
            instance().addPost(Service.Post(courseId, text, order)).enqueue(defaultCallback)
        }
    }

    fun deletePost(
        context: Context,
        postId: Int,
        defaultCallback: DefaultCallback<Service.Status>
    ) {
        getUserId(context)?.let {
            instance().deletePost(Service.PostDelete(postId)).enqueue(defaultCallback)
        }
    }

    fun editPost(
        context: Context,
        postId: Int,
        text: String,
        order: Int,
        defaultCallback: DefaultCallback<Service.PostRes>
    ) {
        getUserId(context)?.let {
            instance().editPost(Service.PostEdit(postId, text, order)).enqueue(defaultCallback)
        }
    }

    fun getCourses(context: Context, defaultCallback: DefaultCallback<Service.CoursesRes>) {
        getUserId(context)?.let {
            instance().getCourses().enqueue(defaultCallback)
        }
    }

    fun getMyCourses(context: Context, defaultCallback: DefaultCallback<Service.CoursesRes>) {
        getUserId(context)?.let {
            instance().getMyCourses(Service.User(it)).enqueue(defaultCallback)
        }
    }

}
