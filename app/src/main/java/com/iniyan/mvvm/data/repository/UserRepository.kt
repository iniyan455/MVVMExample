package com.iniyan.mvvm.data.repository

import com.iniyan.mvvm.data.db.AppDatabase
import com.iniyan.mvvm.data.db.entities.User
import com.iniyan.mvvm.data.network.MyApi
import com.iniyan.mvvm.data.network.SafeApiRequest
import com.iniyan.mvvm.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponse {
        return apiRequest{ api.userSignup(name, email, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}