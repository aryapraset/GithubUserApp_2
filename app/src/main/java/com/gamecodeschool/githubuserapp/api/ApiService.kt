package com.gamecodeschool.githubuserapp.api

import com.gamecodeschool.githubuserapp.BuildConfig.KEY
import com.gamecodeschool.githubuserapp.apiresponse.UserDetailGitResponse
import com.gamecodeschool.githubuserapp.apiresponse.UserGitResponse
import com.gamecodeschool.githubuserapp.apiresponse.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object{
       const val key = "Authorization: token ${KEY}"
    }
    @Headers (key)
    @GET("search/users")
    fun getSearchGitUser(
        @Query("q") query: String
    ): Call<UserGitResponse>

    @Headers(key)
    @GET("users/{username}")
    fun getDetailUserGit(
        @Path("username") username: String
    ): Call<UserDetailGitResponse>

    @Headers(key)
    @GET("users/{username}/followers")
    fun getFollower(
        @Path("username") username: String
    ): Call<List<UserItem>>

    @Headers(key)
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<UserItem>>
}