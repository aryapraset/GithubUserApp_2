package com.gamecodeschool.githubuserapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamecodeschool.githubuserapp.adapter.ListUserAdapter
import com.gamecodeschool.githubuserapp.apiresponse.UserItem
import com.gamecodeschool.githubuserapp.databinding.FragmentFollowingBinding
import com.gamecodeschool.githubuserapp.viewmodel.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_following) {
    private var _binding : FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: ListUserAdapter
    private lateinit var dtUser: String
    private val list = ArrayList<UserItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dtUser = DetailUserActivity.DT_USER
        Log.d("detail", dtUser)
        _binding = FragmentFollowingBinding.bind(view)

        adapter = ListUserAdapter(list)

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]
        viewModel.setFollowingDetail(dtUser)
        viewModel.detailFollowingGitUser.observe(viewLifecycleOwner) {
            if(it!=null){
                adapter.setList(it as ArrayList<UserItem>)
                showLoading(state = false)
                binding.ftUser.layoutManager = LinearLayoutManager(activity)
                binding.ftUser.setHasFixedSize(true)
                binding.ftUser.adapter = adapter
            }
        }
        viewModel.loadingListUser.observe(viewLifecycleOwner,{
            showLoading(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }



}