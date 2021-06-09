package com.demo.androiddemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.androiddemo.R
import com.demo.androiddemo.databinding.ActivityMainBinding
import com.demo.androiddemo.data.local.model.UserData
import com.demo.androiddemo.ui.viewmodel.adapter.UserListAdapter
import com.demo.androiddemo.ui.viewmodel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private var adapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main) as ViewDataBinding?
        val mActivitySplashBinding = binding as ActivityMainBinding
        mActivitySplashBinding.viewModel = viewModel
        mActivitySplashBinding.lifecycleOwner = this
        super.onCreate(savedInstanceState)
        initializeData()
        setUserData()
    }


    private fun initializeData() {
        viewModel.userLiveData.observe(this, observer)
    }

    private val observer = Observer<List<UserData>> { userList ->
        if (userList.isNotEmpty()) {
            viewModel.isLoading.value = false
            adapter?.setUserData(userList = userList as ArrayList<UserData>)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.userLiveData.removeObserver(observer)
    }

    private fun setUserData() {
        val llm = LinearLayoutManager(this)
        adapter = UserListAdapter()
        rvUsers?.setHasFixedSize(true)
        rvUsers?.layoutManager = llm
        rvUsers?.adapter = adapter
    }

}