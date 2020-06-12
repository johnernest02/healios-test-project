package com.healiostestproject.android.backend.room

import androidx.room.*
import com.healiostestproject.android.models.User

@Dao
interface UsersDao {

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM user WHERE id=:id")
    suspend fun getUserByID(id: Int): User

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}