package com.fourello.itunesapi.ApiUtils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiUtils {


    companion object{
        var retrofit1: Retrofit? = null;

        public fun getOkHttp(): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .build()

            return okHttpClient
        }


        public fun getRetrofitClient1(baseUrl: String): Retrofit {
            //nullchecker if retrofit 1 is null
            if (retrofit1 == null){
                var gson = GsonBuilder()
                    .setLenient()
                    .create()
                retrofit1 = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getOkHttp())
                    .build()
            }
            return retrofit1 as Retrofit

        }
    }



}