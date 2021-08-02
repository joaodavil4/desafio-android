package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User

interface UserRepository {
    suspend fun getUsers() : List<User>
}