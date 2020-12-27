package com.fourello.itunesapi.ApiUtils

import com.fourello.itunesapi.Models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApiService {

    @GET("search")
    fun searchSong(
        @Query("term") input: String?,
        @Query("limit") limit: Int?,
        @Query("country") countryCode: String?
    ): Call<SearchResponse>


}