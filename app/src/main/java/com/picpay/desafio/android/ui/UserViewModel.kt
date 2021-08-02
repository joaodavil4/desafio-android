package com.picpay.desafio.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) :ViewModel() {

    val usersMutableLiveData = MutableLiveData<List<User>>()

    fun getUsersCoroutines() {
        viewModelScope.launch(Main)
        {
            try {
                val response = repository.getUsers()
                usersMutableLiveData.value = response
            } catch (exception: Exception) {
                usersMutableLiveData.value = null
            }
        }
    }

    class UserViewModelFactory(
        private val repository: UserRepositoryImpl
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(repository) as T
        }

    }
}