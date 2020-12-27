package com.fourello.itunesapi.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fourello.itunesapi.Constants.Constants
import com.fourello.itunesapi.Models.SearchData
import com.fourello.itunesapi.R
import com.fourello.itunesapi.Utils.DateTimeUtils
import kotlinx.android.synthetic.main.activity_song_details.*


class ActivitySongDetails: AppCompatActivity() {

    var songData: SearchData? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_details)


        getActivityIntent()
        back.setOnClickListener {
            finish()
        }

    }


    fun getActivityIntent(){
        songData = intent.getSerializableExtra(Constants.SONGDATA) as SearchData

        Glide.with(this)
            .load(songData?.artworkUrl100)
            .dontTransform()
            .into(thumbnail)




        songData?.collectionName.let { if (it.isNullOrEmpty()) linearCollection.visibility = View.GONE else txtCollectionName.text = it}
        songData?.trackName.let { if (it.isNullOrEmpty()) linearTrackName.visibility = View.GONE else txtTrackName.text = it}
        songData?.artistName.let {  if (it.isNullOrEmpty()) linearArtist.visibility = View.GONE else txtTrackArtist.text = it}
        songData?.trackPrice.let {  if (it == null) linearTrackPrice.visibility = View.GONE else txtPrice.text = "$it ${songData?.currency}"}
        songData?.releaseDate.let { if (it.isNullOrEmpty())  linearReleaseDate.visibility = View.GONE else  txtReleaseDate.text = DateTimeUtils.getReleaseDate(songData?.releaseDate)}
        songData?.primaryGenreName.let { if (it.isNullOrEmpty()) linearGenre.visibility = View.GONE else txtGenre.text = songData?.primaryGenreName}

        songData?.trackTimeMillis.let { if (it == null) linearTime.visibility = View.GONE else txtTrackTime.text = DateTimeUtils.convertMillsToTime(songData?.trackTimeMillis!!)}

        if (!songData?.trackViewUrl.isNullOrEmpty()){
            linearTrackName.setOnClickListener {
                openExternalUrl(songData?.trackViewUrl)
            }
        }



//        txtExplicite.text = songData?.trackExplicitness
        songData?.trackExplicitness.let {
            if (!it.isNullOrEmpty()){
                if (it.equals("notExplicit")){
                    txtExplicite.text = "Not Explicit"
                }else if (it.equals("Explicit")){
                    txtExplicite.text = "Explicit"
                }else{
                    txtExplicite.text = songData?.trackExplicitness
                }
            }else{
                linearExplicit.visibility = View.GONE
            }
        }


        songData?.artistViewUrl.let { if (it.isNullOrEmpty()) arrowArtist.visibility = View.GONE else linearArtist.setOnClickListener {
            openExternalUrl(songData?.artistViewUrl)
        }}

        songData?.collectionViewUrl.let { if (it.isNullOrEmpty()) arrowCollectionName.visibility = View.GONE else linearCollection.setOnClickListener {
            openExternalUrl(songData?.collectionViewUrl)
        }}

    }

    fun openExternalUrl(url: String?){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.exitfrom, R.anim.exitto)
    }


}