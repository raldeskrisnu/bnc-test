package com.raldes.bnc.ui.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.raldes.bnc.R
import com.raldes.bnc.ui.BaseActivity
import com.raldes.bnc.ui.adapter.MovieBottomBarNavigationAdapter
import com.raldes.bnc.ui.fragment.ListMovieFragment
import com.raldes.bnc.ui.fragment.WishListMovieFragment
import com.raldes.bnc.ui.adapter.OnPageChangeAdapter

class MovieActivity : BaseActivity() {

    @BindView(R.id.bottomNav)
    lateinit var bottomNavigationView: BottomNavigationView

    @BindView(R.id.viewpager)
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))

        val adapter = MovieBottomBarNavigationAdapter(supportFragmentManager)
        adapter.addFragment(ListMovieFragment())
        adapter.addFragment(WishListMovieFragment())
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : OnPageChangeAdapter(0) {
            override fun onPageSelected(lastposition: Int, position: Int) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true)
                bottomNavigationView.getMenu().getItem(lastposition).setChecked(false)
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> viewPager.setCurrentItem(0)
                R.id.menu_wishlist -> viewPager.setCurrentItem(1)
            }
            true
        })

    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }
}