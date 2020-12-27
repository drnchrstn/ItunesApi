package com.fourello.itunesapi.ApiUtils

class ApiBridge {

    companion object{
        fun getApiServiceClient1(baseUrl: String): ItunesApiService{
            return ApiUtils.getRetrofitClient1(baseUrl).create(ItunesApiService::class.java)
        }
    }


}