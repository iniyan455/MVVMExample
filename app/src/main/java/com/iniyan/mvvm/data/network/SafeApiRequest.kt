package com.iniyan.mvvm.data.network

import com.iniyan.mvvm.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {


    /** Api request return as T call also another suspend function makes generic checking response is successfull or error  **/
    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            // error handling
            // example json {"isSuccessful":false,"message":"Invalid email or password"}
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let{
                try{
                    // based on api it will change
                    message.append(JSONObject(it).getString("message"))
                }catch(e: JSONException){ }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")


            throw ApiException(message.toString())
        }
    }

}