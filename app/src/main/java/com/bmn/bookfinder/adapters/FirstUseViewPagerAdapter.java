package com.bmn.bookfinder.adapters;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bmn.bookfinder.R;

import org.jetbrains.annotations.NotNull;

public class FirstUseViewPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private int[] layouts;

    public FirstUseViewPagerAdapter(Context context) {
        layouts = new int[]{
                R.layout.slider_1_first_use,
                R.layout.slider_2_first_use,
                R.layout.slider_3_first_use,};
        layoutInflater = LayoutInflater.from(context);
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
