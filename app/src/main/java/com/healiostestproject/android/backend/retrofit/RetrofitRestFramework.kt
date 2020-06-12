package com.healiostestproject.android.backend.retrofit

import android.app.Application
import com.google.gson.Gson
import com.healiostestproject.android.backend.RestFramework
import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitRestFramework(
    context: Application
) : RestFramework {

    val apiService: APIService

    val client: OkHttpClient

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        client = builder.build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .build()

        apiService = retrofit.create(APIService::class.java)
    }

    override suspend fun loadPosts(): List<Post> = apiService.loadPostsAsync()

    override suspend fun loadUsers(): List<User> = apiService.loadUsersAsync()

    override suspend fun loadComments(): List<Comment> = apiService.loadCommentsAsync()
}