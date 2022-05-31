package com.gamecodeschool.githubuserapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamecodeschool.githubuserapp.adapter.ListUserAdapter
import com.gamecodeschool.githubuserapp.apiresponse.UserItem
import com.gamecodeschool.githubuserapp.databinding.FragmentFollowerBinding
import com.gamecodeschool.githubuserapp.viewmodel.FollowerViewModel

class FollowerFragment : Fragment(R.layout.fragment_follower){
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowerViewModel
    private lateinit var adapter: ListUserAdapter
    private lateinit var dtUser: String
    private val list = ArrayList<UserItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dtUser = DetailUser.DT_USER
        Log.d("it", dtUser)
        _binding = FragmentFollowerBinding.bind(view)

        adapter = ListUserAdapter(list)
        adapter.notifyDataSetChanged()

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowerViewModel::class.java)
        viewModel.setFollowerDetail(dtUser)
        viewModel.detailFollowerGitUser.observe(viewLifecycleOwner, {
            if(it!=null){
                adapter.setList(it as ArrayList<UserItem>)
                showLoading(false)
                binding.ftUser.layoutManager = LinearLayoutManager(activity)
                binding.ftUser.setHasFixedSize(true)
                binding.ftUser.adapter = adapter
            }
        })
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