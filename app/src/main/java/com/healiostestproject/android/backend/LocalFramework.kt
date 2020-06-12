package com.healiostestproject.android.backend

import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User

interface LocalFramework {

    suspend fun getPosts(): List<Post>

    suspend fun getUser(userID: Int): User

    suspend fun getCommentsForPost(postID: Int): List<Comment>

    suspend fun insertPosts(posts: List<Post>)

    suspend fun insertUsers(users: List<User>)

    suspend fun insertComments(comments: List<Comment>)
}