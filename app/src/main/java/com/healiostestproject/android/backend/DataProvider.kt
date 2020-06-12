package com.healiostestproject.android.backend

import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User

interface DataProvider {

    suspend fun loadPosts(): List<Post>

    suspend fun getUser(id: Int): User

    suspend fun getComments(postID: Int): List<Comment>
}