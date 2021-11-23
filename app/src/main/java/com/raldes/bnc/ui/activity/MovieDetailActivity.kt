package com.raldes.bnc.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import com.raldes.bnc.R
import com.raldes.bnc.di.component.DaggerMovieDetailActivityComponent
import com.raldes.bnc.di.module.MovieModule
import com.raldes.bnc.ui.BaseActivity
import com.raldes.bnc.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

class MovieDetailActivity: BaseActivity() {

    @BindView(R.id.title_movie)
    lateinit var titleMovie: TextView

    @BindView(R.id.image_movie)
    lateinit var imageMovie:ImageView

    @BindView(R.id.image_wishlist)
    lateinit var imageWishList: ImageView

    @BindView(R.id.ratingBar)
    lateinit var ratingBar: RatingBar

    @BindView(R.id.genre)
    lateinit var genre: TextView

    @BindView(R.id.description_movie)
    lateinit var descriptionMovie: TextView

    @BindView(R.id.actor_list)
    lateinit var actorList: TextView

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idExtra:Int = intent.getIntExtra("idMovie",0)
        setupLiveData()
        movieViewModel.getMovieDetail(idExtra)
    }

    override fun iniateViewModel() {
        movieViewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
    }

    override fun resolveDaggerDependency() {
        DaggerMovieDetailActivityComponent.builder()
            .applicationComponent(applicationComponent())
            .movieModule(MovieModule())
            .build().inject(this)
    }

    override fun getContentView(): Int {
        return R.layout.activity_movie_detail
    }

    private fun setupLiveData() {
        val builder = StringBuilder()
        movieViewModel.apply {
            getDetailMovie.observe(this@MovieDetailActivity, Observer {
                titleMovie.text = it.data.title
                descriptionMovie.text = it.data.desc
                genre.text = it.data.genre + " - " + it.data.duration
                ratingBar.setRating(it.data.rating.toFloat())
                ratingBar.setIsIndicator(true)

                for ((i, item) in it.data.starring.withIndex()) {
                    builder.append(item)
                    builder.append(", ")
                }

                actorList.text = "Actor List : $builder"

                Picasso.get()
                    .load(it.data.imageLarge)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imageMovie)

            })
        }
    }

}