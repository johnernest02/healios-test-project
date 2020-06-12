package com.healiostestproject.android.backend

import android.app.Application
import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User

class HealiosDataProvider(
    val context: Application,
    val localFramework: LocalFramework,
    val restFramework: RestFramework
) : DataProvider {

    override suspend fun loadPosts(): List<Post> {
        var posts = localFramework.getPosts()
        if (posts.isEmpty()) {
            posts = restFramework.loadPosts()
            localFramework.insertPosts(posts) // cache to persistence
        }

        return posts
    }

    override suspend fun getUser(userID: Int): User {
        var user = localFramework.getUser(userID)
        if (user == null) {
            var usersList = restFramework.loadUsers()
            localFramework.insertUsers(usersList)
        }

        user = localFramework.getUser(userID)

        return user
    }

    override suspend fun getComments(postID: Int): List<Comment> {
        var comments = localFramework.getCommentsForPost(postID)

        if (comments.isEmpty()) {
            comments = restFramework.loadComments()
            localFramework.insertComments(comments)
        }

        comments = localFramework.getCommentsForPost(postID)

        return comments
    }
}