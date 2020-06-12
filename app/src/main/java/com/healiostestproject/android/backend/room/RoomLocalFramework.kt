package com.healiostestproject.android.backend.room

import android.app.Application
import androidx.room.Room
import com.healiostestproject.android.backend.LocalFramework
import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User

class RoomLocalFramework(
    context: Application
) : LocalFramework {

    val database = Room.databaseBuilder(
        context,
        HealiosTestDatabase::class.java,
        "healios-database"
    ).build()

    override suspend fun getPosts(): List<Post> = database.postsDao().getPosts()

    override suspend fun getUser(userID: Int): User = database.usersDao().getUserByID(userID)

    override suspend fun getCommentsForPost(postID: Int): List<Comment> =
        database.commentsDao().getCommentsByPostID(postID)

    override suspend fun insertPosts(posts: List<Post>) {
        posts.forEach {
            database.postsDao().insertPost(it)
        }
    }

    override suspend fun insertUsers(users: List<User>) {
        users.forEach {
            database.usersDao().insertUser(it)
        }
    }

    override suspend fun insertComments(comments: List<Comment>) {
        comments.forEach {
            database.commentsDao().insertComment(it)
        }
    }
}