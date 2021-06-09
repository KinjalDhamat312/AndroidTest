package com.demo.androiddemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.androiddemo.data.local.model.UserData

/**
 * The Room Database that contains the User table.
 */
@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
