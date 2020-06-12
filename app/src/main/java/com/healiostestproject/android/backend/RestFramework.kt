package com.healiostestproject.android.backend

import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User
import io.reactivex.Single

interface RestFramework {

    suspend fun loadPosts(): List<Post>

    suspend fun loadUsers(): List<User>

    suspend fun loadComments(): List<Comment>
}