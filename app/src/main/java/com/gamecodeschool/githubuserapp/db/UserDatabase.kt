package com.gamecodeschool.githubuserapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteUserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase()  {
    abstract fun favoriteUserDao(): FavoriteUserDao

    companion object{
        @Volatile
        var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database")
                        .build()
                }
            }
            return INSTANCE as UserDatabase
        }
    }
}