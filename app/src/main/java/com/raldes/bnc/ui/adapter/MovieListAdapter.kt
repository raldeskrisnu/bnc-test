package com.raldes.bnc.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raldes.bnc.R
import com.raldes.bnc.model.DataMovie

class MovieListAdapter(
    private val onItemClicked:(dataMovie: DataMovie?) -> Unit,
    private val onWishListClicked:(dataMovie: DataMovie?) -> Unit
    ):RecyclerView.Adapter<MovieListViewHolder>() {

    var movieList: List<DataMovie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
            ,onItemClicked,onWishListClicked)
    }

    override fun getItemCount(): Int {
      if(movieList !=null) {
          return movieList!!.size
      }

        return 0
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.onBind(movieList?.get(position))
    }

    fun setMovieListData(movieList: List<DataMovie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}