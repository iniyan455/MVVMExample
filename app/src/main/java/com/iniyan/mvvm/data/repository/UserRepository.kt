package com.iniyan.mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iniyan.mvvm.data.db.AppDatabase
import com.iniyan.mvvm.data.db.entities.User
import com.iniyan.mvvm.data.network.MyApi
import com.iniyan.mvvm.data.network.SafeApiRequest
import com.iniyan.mvvm.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    /** bad implementation **/
    fun userLoginOld(email: String,password: String):LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        api.userLoginOld(email,password).enqueue(object :Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                loginResponse.value = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    loginResponse.value = response.body()?.string()
                }
                else {
                    loginResponse.value = response.body()?.string()
                }
            }

        })

        return loginResponse
    }


    /**
     * Response AuthResponse
     * Suspend function only allows coroutine or any another suspend function
     */
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return  apiRequest {

            /**
             *  MyApi().userLogin(email, password)
             *  bad idea instead of use inject through constructor
             */

            api.userLogin(email, password)

        }
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