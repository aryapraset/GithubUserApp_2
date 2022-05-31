package com.gamecodeschool.githubuserapp


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gamecodeschool.githubuserapp.adapter.SectionPagerAdapter
import com.gamecodeschool.githubuserapp.databinding.ActivityDetailUserBinding
import com.gamecodeschool.githubuserapp.viewmodel.DetailUserViewModel
import com.gamecodeschool.githubuserapp.viewmodel.FollowerViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val viewModel: DetailUserViewModel by viewModels()


    companion object{
        var DT_USER = "dtUser"
        const val DETAIL_USER = "detail_user"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting Section
        val sectionPagerAdapter = SectionPagerAdapter(this, data = Bundle())
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val dtUser = intent.getStringExtra(DETAIL_USER)
        Log.d ("dtUser", dtUser.toString())
        DT_USER = dtUser.toString()
        val bundle = Bundle()
        bundle.putString(DETAIL_USER, dtUser)

        supportActionBar?.elevation = 0f

        dtUser?.let {
            viewModel.setUserDetail(it)
        }

        viewModel.detailGitUser.observe(this){
            Log.d("userName", it.toString())
            binding.userName.text = dtUser
            binding.name.text = it.name
            Glide.with(this)
                .load(it.avatarUrl)
                .centerCrop()
                .into(binding.imgPhoto)
            binding.repository.text = "${it.publicRepos} Repository"
            binding.location.text = it.location
            binding.company.text = it.company
            binding.follower.text = "${it.followers} Followers"
            binding.following.text = "${it.following} Following"

        }

    }
}