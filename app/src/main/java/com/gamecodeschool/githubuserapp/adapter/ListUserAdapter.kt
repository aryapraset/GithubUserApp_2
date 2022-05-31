package com.gamecodeschool.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamecodeschool.githubuserapp.apiresponse.UserItem
import com.gamecodeschool.githubuserapp.databinding.DataTabelBinding


class ListUserAdapter(list: ArrayList<UserItem>) : RecyclerView.Adapter<ListUserAdapter.UserViewHolder>(){
    private val list = ArrayList<UserItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(userGit: ArrayList<UserItem>){
        list.clear()
        list.addAll(userGit)
        notifyDataSetChanged()
    }
    inner class UserViewHolder(val binding: DataTabelBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(userItem: UserItem){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(userItem)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(userItem.avatarUrl)
                    .centerCrop()
                    .into(imgPhoto)
                name.text = userItem.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = DataTabelBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return(UserViewHolder(view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: UserItem)
    }
}