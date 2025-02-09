package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): List<User>
}