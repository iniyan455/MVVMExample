package com.iniyan.mvvm.data.network

import com.iniyan.mvvm.data.network.responses.AuthResponse
import com.iniyan.mvvm.data.network.responses.QuotesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
            @Field("email") email: String,
            @Field("password") password: String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
            @Field("name") name: String,
            @Field("email") email: String,
            @Field("password") password: String
    ) : Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes() : Response<QuotesResponse>

    companion object{
        operator fun invoke(
                networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(Level.BODY)

            val okkHttpclient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(networkConnectionInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                    .client(okkHttpclient)
                    .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyApi::class.java)

        }
    }

}

