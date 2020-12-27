package com.fourello.itunesapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fourello.itunesapi.Activities.ActivitySongDetails
import com.fourello.itunesapi.Adapters.SongAdapter
import com.fourello.itunesapi.ApiUtils.ApiRetrofit
import com.fourello.itunesapi.Constants.Constants
import com.fourello.itunesapi.Helpers.PrefsHelper
import com.fourello.itunesapi.Interface.OnSearchClick
import com.fourello.itunesapi.Models.SearchData
import com.fourello.itunesapi.Models.SearchResponse
import com.fourello.itunesapi.ViewModels.SongsViewmodel

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var songAdapter: SongAdapter? = null
    var songResult: MutableList<SearchData> = ArrayList()

    var songViewmodel: SongsViewmodel? = null
    val mainContext = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        if (PrefsHelper.getLastSearch() != null && PrefsHelper.getLastSearch()?.size!! > 0){
            songResult.clear()
            songResult.addAll(PrefsHelper.getLastSearch()!!)
        }else{
            linearNoItems.visibility = View.VISIBLE
            songRecycler.visibility = View.GONE
            txtTitle.visibility = View.GONE
        }


        songAdapter = SongAdapter(songResult, mainContext, object: OnSearchClick{
            override fun OnClick(searchData: SearchData) {
                gotoDetails(searchData)
            }
        })
        songViewmodel = ViewModelProvider(this).get(SongsViewmodel::class.java)
        songViewmodel?.getSonglistObserver()?.observe(this,
            Observer { t ->
                if (t != null){
//                    songResult = t
                    songResult.clear()
                    songResult.addAll(t)
                    songAdapter?.notifyDataSetChanged()
                    songRecycler.smoothScrollToPosition(0)
                    if (linearNoItems.visibility == View.VISIBLE){
                        linearNoItems.visibility = View.GONE
                        songRecycler.visibility = View.VISIBLE
                        txtTitle.visibility = View.VISIBLE
                    }


                }
            })

        linearNoItems.setOnClickListener {
            searchView.onActionViewExpanded()
//            searchView.focusable = true
//            searchView.requestFocus()
//            searchView.requestFocusFromTouch()
//            searchView.isIconified = true


        }


        songRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        songRecycler.adapter = songAdapter

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()){
                    showProgressLoading()
                    if (linearNoItems.visibility == View.VISIBLE){
                        linearNoItems.visibility = View.GONE
                    }
                    ApiRetrofit.instance.searchSong(query, object: Callback<SearchResponse>{
                        override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                            doneLoading()
                            Log.v("","")
                            if (songResult.size == 0){
                                linearNoItems.visibility = View.VISIBLE
                            }
                        }

                        override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                            doneLoading()
                            if (response.isSuccessful && response.body() != null){

                                if (!response.body()?.results.isNullOrEmpty()){
                                    songViewmodel?.setSongList(response.body()?.results!!)
                                    PrefsHelper.setLastSearch(response.body())
                                    txtTitle.text = "Result/s for: $query"
//                                    songResult.clear()
//                                    songResult.addAll(response.body()?.results!!)
//                                    songAdapter?.notifyDataSetChanged()
                                }

                            }else{
                                Log.v("","")
                            }
                        }
                    })
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.v("","")
                return false
            }
        })


    }

    fun showProgressLoading(){
        progressLoading.visibility = View.VISIBLE
        songRecycler.visibility = View.GONE
        txtTitle.visibility = View.GONE
    }

    fun doneLoading(){
        progressLoading.visibility = View.GONE
        songRecycler.visibility = View.VISIBLE
        txtTitle.visibility = View.VISIBLE
    }

    fun gotoDetails(searchData: SearchData){
        val intent = Intent(this, ActivitySongDetails::class.java)
        intent.putExtra(Constants.SONGDATA, searchData)
        startActivity(intent)
        overridePendingTransition(R.anim.enterfrom, R.anim.enterto)
    }
}