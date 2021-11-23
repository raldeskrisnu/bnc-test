package com.raldes.bnc.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.raldes.bnc.R
import com.raldes.bnc.model.DataMovie
import com.squareup.picasso.Picasso

class MovieListViewHolder(itemView: View,
                          private val onItemClicked: (dataMovie: DataMovie?) -> Unit,
                          private val onClickWishList: (dataMovie: DataMovie?) -> Unit):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    @BindView(R.id.image_movie)
    lateinit var imageMovie: ImageView

    @BindView(R.id.image_wishlist)
    lateinit var imageWishlistUnSelected: ImageView

    @BindView(R.id.image_wishlist_selected)
    lateinit var imageWishListSelected: ImageView

    @BindView(R.id.title_movie)
    lateinit var titleMovie: TextView

    private var dataMovie: DataMovie? = null

    init {
        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener(this)
        imageWishlistUnSelected.setOnClickListener(this)
    }

    fun onBind(dataMovie: DataMovie?) {
        this.dataMovie = dataMovie
        titleMovie.text = dataMovie?.title

        Picasso.get()
            .load(dataMovie?.image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageMovie)

        imageWishlistUnSelected.setOnClickListener {

            if (imageWishlistUnSelected.visibility == View.GONE) {
                imageWishlistUnSelected.visibility = View.VISIBLE
                imageWishListSelected.visibility = View.GONE
            } else {
                imageWishlistUnSelected.visibility = View.GONE
                imageWishListSelected.visibility = View.VISIBLE
            }
        }

        imageWishlistUnSelected.setOnClickListener({
            onClickWishList(dataMovie)
        })
    }

    override fun onClick(p0: View?) {
        onItemClicked(dataMovie)
    }
}