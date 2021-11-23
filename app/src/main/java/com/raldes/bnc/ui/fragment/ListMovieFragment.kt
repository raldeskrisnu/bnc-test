package com.raldes.bnc.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.raldes.bnc.R
import com.raldes.bnc.di.component.DaggerListMovieFragmentComponent
import com.raldes.bnc.di.module.MovieModule
import com.raldes.bnc.model.DataMovie
import com.raldes.bnc.ui.BaseFragment
import com.raldes.bnc.ui.activity.MovieDetailActivity
import com.raldes.bnc.ui.adapter.MovieListAdapter
import com.raldes.bnc.viewmodel.MovieViewModel

class ListMovieFragment : BaseFragment() {

    @BindView(R.id.recylerview_movie)
    lateinit var rvMovie: RecyclerView

    private lateinit var movieViewModel: MovieViewModel

    private var layoutManager: LinearLayoutManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
        movieViewModel.getListMovie()
    }

    private fun setupLiveData() {
        movieViewModel.apply {
            loading.observe(viewLifecycleOwner, Observer {
                if(it) {

                } else {

                }
            })

            layoutManager = LinearLayoutManager(this@ListMovieFragment.context)
            val movieListAdapter = MovieListAdapter({
                onListItemClick(it)
            }, {
                onWishClick(it)
            })
            rvMovie.layoutManager = layoutManager
            rvMovie.adapter = movieListAdapter

            getMovieList.observe(viewLifecycleOwner, Observer {
                if(it !=null) {
                    movieListAdapter.setMovieListData(it)
                }
            })
        }
    }

    override fun iniateViewModel() {
        movieViewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
    }

    override fun getContentView(): Int {
        return R.layout.fragment_list_movie
    }

    override fun resolveDaggerDependency() {
        DaggerListMovieFragmentComponent.builder()
            .applicationComponent(applicationComponent())
            .movieModule(MovieModule())
            .build().inject(this)
    }

    private fun onListItemClick(dataMovie: DataMovie?) {
        val movieDetailActivity = Intent(activity,
            MovieDetailActivity::class.java)
        movieDetailActivity.putExtra("idMovie",dataMovie?.id)
        startActivity(movieDetailActivity)
    }

    private fun onWishClick(dataMovie: DataMovie?) {

    }

}