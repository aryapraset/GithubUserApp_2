package com.gamecodeschool.githubuserapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteUserDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorite(favoriteUser: FavoriteUserEntity)

    @Query("SELECT * FROM favorite_user where bookmarked = 1")
    fun getFavoriteUser(): LiveData<List<FavoriteUserEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_user WHERE id = :id AND bookmarked = 1)")
    fun isUserBookmarked(id: Int): Boolean

    //untuk check
    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM favorite_user WHERE bookmarked = 0")
    fun removeFromFavorite(id: Int): Int
}