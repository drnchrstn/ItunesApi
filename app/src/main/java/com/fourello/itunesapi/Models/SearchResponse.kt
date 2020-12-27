package com.fourello.itunesapi.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchResponse: Serializable {

    @SerializedName("resultCount")
    var resultCount: Int? = null

    @SerializedName("results")
    var results: MutableList<SearchData>? = null



}