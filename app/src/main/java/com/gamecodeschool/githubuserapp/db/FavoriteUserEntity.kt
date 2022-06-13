package com.gamecodeschool.githubuserapp.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_user")
@Parcelize
data class FavoriteUserEntity(
    @PrimaryKey
    val id:Int,

    @ColumnInfo(name = "username")
    val login: String,

    @ColumnInfo(name = "bookmarked")
    var isBookmarked: Boolean

) : Parcelable
