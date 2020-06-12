package com.healiostestproject.android.backend.room

import androidx.room.*
import com.healiostestproject.android.models.Post

@Dao
interface PostsDao {

    @Query("SELECT * FROM post")
    suspend fun getPosts(): List<Post>

    @Insert
    suspend fun insertPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)
}