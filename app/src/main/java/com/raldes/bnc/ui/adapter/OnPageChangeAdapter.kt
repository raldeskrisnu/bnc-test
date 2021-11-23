package com.raldes.bnc.ui.adapter

import androidx.viewpager.widget.ViewPager

abstract class OnPageChangeAdapter(i: Int) : ViewPager.OnPageChangeListener {

    private var firstPosition = i

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        firstPosition = position
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    abstract fun onPageSelected(lastposition: Int, position: Int)
}