package com.gamecodeschool.githubuserapp.apiresponse

import com.google.gson.annotations.SerializedName

data class UserGitResponse(

	@field:SerializedName("total_count")
	val totalCount: Int,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean,

	@field:SerializedName("items")
	val items: List<UserItem>
)

data class UserItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("id")
	val id: Int

)
