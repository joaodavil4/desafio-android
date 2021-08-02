package com.picpay.desafio.android.ui

import android.annotation.SuppressLint
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepositoryImpl

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        setRecyclerView()
        setProgresBar()

        val viewModel = ViewModelProvider(this,
            UserViewModel.UserViewModelFactory(UserRepositoryImpl())) //
            .get(UserViewModel::class.java)

        viewModel.usersMutableLiveData.observe(this, Observer { users ->
            users?.let {
                adapter.notifyDataSetChanged()
                addUsers(it)
            }
        })
        viewModel.getUsersCoroutines()
    }

    private fun addUsers(usersAdd: List<User>) {
        progressBar.visibility = View.GONE
        adapter.users = usersAdd
    }

    private fun setErrorMessage() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, getString(R.string.error),
            Toast.LENGTH_SHORT).show()
    }

    private fun setProgresBar(){
        progressBar = findViewById(R.id.user_list_progress_bar)
        progressBar.visibility = View.VISIBLE
    }

    private fun setRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
