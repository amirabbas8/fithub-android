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

    fun isCoach(context: Context): Boolean {
        return Auth.load(context)?.license_number != 0
    }

    fun insertHistory(
        context: Context,
        type: Int,
        details: String,
        value: String,
        defaultCallback: DefaultCallback<HistoryRes>
    ) = getUserId(context)?.let {
        instance().insertHistory(History(it, type, details, value))
            .enqueue(defaultCallback)
    }

    fun getHistory(
        context: Context,
        defaultCallback: DefaultCallback<HistoriesRes>
    ) = getUserId(context)?.let {
        instance().getHistory(User(it)).enqueue(defaultCallback)
    }


    fun addCourse(
        context: Context,
        name: String,
        price: Int,
        value: String,
        defaultCallback: DefaultCallback<CourseRes>
    ) = getUserId(context)?.let {
        instance().addCourse(Course(it, name, price, value)).enqueue(defaultCallback)
    }


    fun enrollCourse(
        context: Context,
        courseId: Int,
        defaultCallback: DefaultCallback<EnrollmentRes>
    ) = getUserId(context)?.let {
        instance().enrollCourse(Enrollment(it, courseId)).enqueue(defaultCallback)
    }


    fun leftCourse(
        context: Context,
        courseStudentId: Int,
        defaultCallback: DefaultCallback<Status>
    ) = getUserId(context)?.let {
        instance().leftCourse(Leave(courseStudentId)).enqueue(defaultCallback)
    }


    fun addPost(
        context: Context,
        courseId: Int,
        text: String,
        order: Int,
        defaultCallback: DefaultCallback<PostRes>
    ) = getUserId(context)?.let {
        instance().addPost(Post(courseId, text, order)).enqueue(defaultCallback)
    }


    fun deletePost(
        context: Context,
        postId: Int,
        defaultCallback: DefaultCallback<Status>
    ) = getUserId(context)?.let {
        instance().deletePost(PostDelete(postId)).enqueue(defaultCallback)
    }


    fun editPost(
        context: Context,
        postId: Int,
        text: String,
        order: Int,
        defaultCallback: DefaultCallback<PostRes>
    ) = getUserId(context)?.let {
        instance().editPost(PostEdit(postId, text, order)).enqueue(defaultCallback)
    }

    fun getPosts(context: Context, courseId: Int, defaultCallback: DefaultCallback<PostsRes>) =
        getUserId(context)?.let {
            instance().getPosts(Posts(courseId)).enqueue(defaultCallback)
        }

    fun getCourses(context: Context, defaultCallback: DefaultCallback<CoursesRes>) =
        getUserId(context)?.let {
            instance().getCourses().enqueue(defaultCallback)
        }

    fun getMyCourses(context: Context, defaultCallback: DefaultCallback<CoursesRes>) =
        getUserId(context)?.let {
            instance().getMyCourses(User(it)).enqueue(defaultCallback)
        }

}
