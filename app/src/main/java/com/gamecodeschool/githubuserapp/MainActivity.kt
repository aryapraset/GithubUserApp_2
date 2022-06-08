package com.gamecodeschool.githubuserapp


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamecodeschool.githubuserapp.adapter.ListUserAdapter
import com.gamecodeschool.githubuserapp.apiresponse.UserItem
import com.gamecodeschool.githubuserapp.databinding.ActivityMainBinding
import com.gamecodeschool.githubuserapp.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: ListUserAdapter
    private val list = ArrayList<UserItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListUserAdapter(list)

        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserItem) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it. putExtra(DetailUserActivity.DETAIL_USER, data.login)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UserViewModel::class.java]
        viewModel.gitUser.observe(this) {
            if(it!=null){
                adapter.setList(it as ArrayList<UserItem>)
                showLoading(state = false)
                binding.ftUser.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.ftUser.setHasFixedSize(true)
                binding.ftUser.adapter = adapter
            }
        }
        viewModel.loadingListUser.observe(this){
            showLoading(it)
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.searchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                list.clear()
                viewModel.setGitUser(query)
                binding.ftUser.adapter = ListUserAdapter(list)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                list.clear()
                viewModel.setGitUser(newText)
                binding.ftUser.adapter = ListUserAdapter(list)
                return true
            }
        })

    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }

}
