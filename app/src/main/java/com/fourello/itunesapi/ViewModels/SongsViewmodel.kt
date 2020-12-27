package com.fourello.itunesapi.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fourello.itunesapi.ApiUtils.ApiRetrofit
import com.fourello.itunesapi.Models.SearchData
import com.fourello.itunesapi.Models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongsViewmodel: ViewModel() {

     var songList: MutableLiveData<MutableList<SearchData>> = MutableLiveData()


    fun getSonglistObserver(): MutableLiveData<MutableList<SearchData>>{
        return songList
    }

    fun setSongList(list: MutableList<SearchData>){
//        songList.value = list
        songList.postValue(list)
    }

//    fun callApi(input: String?){
//        ApiRetrofit.instance.searchSong(input, object: Callback<SearchResponse>{
//            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
//                Log.v("","")
//            }
//
//            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
//                if (response.isSuccessful && response.body() != null && response.body()?.results?.isNotEmpty()!!){
//                    songList.value = response.body()
//                }else{
//
//                }
//            }
//        })
//    }


}