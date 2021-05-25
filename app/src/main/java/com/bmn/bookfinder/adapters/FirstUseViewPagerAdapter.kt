package com.bmn.bookfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bmn.bookfinder.R

class FirstUseViewPagerAdapter(context: Context?) : PagerAdapter() {
    private val layoutInflater: LayoutInflater
    private val layouts: IntArray
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(layouts[position], container, false)
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

    init {
        layouts = intArrayOf(
            R.layout.slider_1_first_use,
            R.layout.slider_2_first_use,
            R.layout.slider_3_first_use
        )
        layoutInflater = LayoutInflater.from(context)
    }
}