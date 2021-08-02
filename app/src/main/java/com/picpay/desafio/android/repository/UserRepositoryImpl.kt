package com.picpay.desafio.android.repository

import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.api.RetrofitBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UserRepositoryImpl : UserRepository {

    override suspend fun getUsers() =
        withContext(IO) {
            RetrofitBuilder().service.getUsers()
        }
}