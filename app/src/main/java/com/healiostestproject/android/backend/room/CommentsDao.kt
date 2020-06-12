package com.healiostestproject.android.backend.room

import androidx.room.*
import com.healiostestproject.android.models.Comment

@Dao
interface CommentsDao {

    @Query("SELECT * FROM comment")
    suspend fun getComments(): List<Comment>

    @Query("SELECT * FROM comment WHERE postId=:postID")
    suspend fun getCommentsByPostID(postID: Int): List<Comment>

    @Insert
    suspend fun insertComment(comment: Comment)

    @Update
    suspend fun updateComment(comment: Comment)

    @Delete
    suspend fun deleteComment(comment: Comment)
}