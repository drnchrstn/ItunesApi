package com.fourello.itunesapi.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fourello.itunesapi.Interface.OnSearchClick
import com.fourello.itunesapi.Models.SearchData
import com.fourello.itunesapi.R

class SongAdapter(searchList: MutableList<SearchData>, context: Context, onclick: OnSearchClick): RecyclerView.Adapter<SongAdapter.ItemViewHolder>(){

    var list = searchList
    var mainContext = context

    var clickListener = onclick




    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var thumbnail = itemView.findViewById<ImageView>(R.id.imgTrack)
        var cardLayout = itemView.findViewById<CardView>(R.id.cardLayout)
        var txtTrackName = itemView.findViewById<TextView>(R.id.txtTrackName)
        var txtTrackArtist = itemView.findViewById<TextView>(R.id.txtTrackArtist)
        var txtPrice = itemView.findViewById<TextView>(R.id.txtPrice)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var songData = list[position]
        Glide.with(mainContext)
            .load(songData.artworkUrl100)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.thumbnail)

        holder.txtTrackName.text = songData.trackName
        holder.txtPrice.text = "${songData.currency} ${songData.trackPrice}"
        holder.txtTrackArtist.text = songData.artistName
        holder.cardLayout.setOnClickListener {
            clickListener.OnClick(songData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(mainContext).inflate(R.layout.item_song_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}