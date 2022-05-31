package com.gamecodeschool.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamecodeschool.githubuserapp.api.ApiConfig
import com.gamecodeschool.githubuserapp.apiresponse.UserDetailGitResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {

    private val _loadingListUser = MutableLiveData<Boolean>()
    val loadingListUser: LiveData<Boolean> = _loadingListUser

    private val _detailGitUser = MutableLiveData<UserDetailGitResponse>()
    val detailGitUser: LiveData<UserDetailGitResponse> = _detailGitUser

    fun setUserDetail(username: String){
        _loadingListUser.value = true
        val client = ApiConfig.getApiService().getDetailUserGit(username)
        client.enqueue(object : Callback<UserDetailGitResponse>{
            override fun onResponse(
                call: Call<UserDetailGitResponse>,
                response: Response<UserDetailGitResponse>
            ) {
                _loadingListUser.value=false
                if(response.isSuccessful){
                    _detailGitUser.postValue(response.body())
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserDetailGitResponse>, t: Throwable) {
                _loadingListUser.value=false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }


    companion object{
        private const val TAG = "DetailUserViewModel"
    }
}
