package com.gamecodeschool.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamecodeschool.githubuserapp.api.ApiConfig
import com.gamecodeschool.githubuserapp.apiresponse.UserGitResponse
import com.gamecodeschool.githubuserapp.apiresponse.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {
    private val _gitUser =  MutableLiveData<List<UserItem>>()
    val gitUser: LiveData<List<UserItem>> = _gitUser

    private val _loadingListUser = MutableLiveData<Boolean>()
    val loadingListUser: LiveData<Boolean> = _loadingListUser

    fun setGitUser(q: String){
        _loadingListUser.value=true
        val client = ApiConfig.getApiService().getSearchGitUser(q)
        client.enqueue(object : Callback<UserGitResponse>{
            override fun onResponse(
                call: Call<UserGitResponse>,
                response: Response<UserGitResponse>
            ) {
                _loadingListUser.value=false
                if(response.isSuccessful){
                    _gitUser.postValue(response.body()?.items)
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserGitResponse>, t: Throwable) {
                _loadingListUser.value=false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    companion object{
        private const val TAG = "UserViewModel"
    }
}
