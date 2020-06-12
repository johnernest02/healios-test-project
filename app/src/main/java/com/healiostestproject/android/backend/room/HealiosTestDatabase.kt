package com.healiostestproject.android.backend.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User

@Database(
    entities = [User::class, Post::class, Comment::class],
    version = 1
)
@TypeConverters(AddressConverter::class, GeoConverter::class, CompanyConverter::class)
abstract class HealiosTestDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun postsDao(): PostsDao
    abstract fun commentsDao(): CommentsDao
}