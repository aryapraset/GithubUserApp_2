package com.gamecodeschool.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamecodeschool.githubuserapp.api.ApiConfig
import com.gamecodeschool.githubuserapp.apiresponse.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {

    private val _loadingListUser = MutableLiveData<Boolean>()
    val loadingListUser: LiveData<Boolean> = _loadingListUser

    private val _detailFollowingGitUser = MutableLiveData<List<UserItem>>()
    val detailFollowingGitUser: LiveData<List<UserItem>> = _detailFollowingGitUser

    fun setFollowingDetail(username: String) {
        _loadingListUser.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<UserItem>>{
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                _loadingListUser.value=false
                if(response.isSuccessful){
                    Log.d(TAG, response.body().toString())
                    _detailFollowingGitUser.postValue(response.body())
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                _loadingListUser.value=false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
    companion object{
        private const val TAG = "FollowingViewModel"
    }
}
