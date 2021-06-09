package com.demo.androiddemo.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.androiddemo.data.local.model.UserData


/**
 * Data Access Object for the user table.
 */
@Dao
interface UserDao {

    /**
     * Insert a user in the database. If the task already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: List<UserData>)

    @Query("SELECT * FROM User")
    fun getUsers(): LiveData<List<UserData>>
}
