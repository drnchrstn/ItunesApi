package com.fourello.itunesapi.ApiUtils

import com.fourello.itunesapi.Models.SearchResponse
import retrofit2.Callback

class ApiRetrofit {

    val baseUrl = "https://itunes.apple.com/"
    private val apiService = ApiBridge.getApiServiceClient1(baseUrl)


    private object HOLDER{
        val instance = ApiRetrofit()
    }


    companion object{
        val instance: ApiRetrofit by lazy { HOLDER.instance }
    }


    fun searchSong(userInput: String?, callback: Callback<SearchResponse>){
        apiService.searchSong(userInput, 60, "PH").enqueue(callback)
    }



}