package com.healiostestproject.android.backend.retrofit

import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {

    @GET("posts")
    suspend fun loadPostsAsync(): List<Post>

    @GET("users")
    suspend fun loadUsersAsync(): List<User>

    @GET("comments")
    suspend fun loadCommentsAsync(): List<Comment>
}