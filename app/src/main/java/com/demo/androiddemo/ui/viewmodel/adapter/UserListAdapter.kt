package com.demo.androiddemo.ui.viewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.demo.androiddemo.R
import com.demo.androiddemo.data.local.model.UserData

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val userList: ArrayList<UserData> = arrayListOf()

    fun setUserData(userList: ArrayList<UserData>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_user,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userData: UserData) {
            binding.setVariable(BR.userData, userData)
            binding.executePendingBindings()
        }
    }
}
