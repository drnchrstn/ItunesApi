package com.fourello.itunesapi.Helpers

import com.fourello.itunesapi.Constants.Constants
import com.fourello.itunesapi.Models.SearchData
import com.fourello.itunesapi.Models.SearchResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pixplicity.easyprefs.library.Prefs

class PrefsHelper {

    companion object{
        fun setLastSearch(lastResponse: SearchResponse?){
            val mGson = GsonBuilder()
                .setLenient()
                .create()

        Prefs.putString(Constants.LASTSEARCH, mGson.toJson(lastResponse))

        }

        fun getLastSearch(): MutableList<SearchData>?{
            var lastResponse = Gson().fromJson(Prefs.getString(Constants.LASTSEARCH, ""), SearchResponse::class.java)
            if (lastResponse != null && !lastResponse.results.isNullOrEmpty()){
                return lastResponse.results
            }else{
                return null
            }


        }

    }
}