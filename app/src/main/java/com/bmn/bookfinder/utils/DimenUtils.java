package com.bmn.bookfinder.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

/**
 * Created by Ndriçim Sadiku on 22 June 2020
 * ndricim@frakton.com
 */
public class DimenUtils {

    private static final String STATUS_BAR_HEIGHT = "status_bar_height";
    private static final String NAVIGATION_BAR_HEIGHT = "navigation_bar_height";


    public static int getStatusBarHeight(Context context) {
        return getSystemRelatedDimen(context, STATUS_BAR_HEIGHT);
    }

    public static int getNavigationBarHeight(Context context) {
        return getSystemRelatedDimen(
                context,
                NAVIGATION_BAR_HEIGHT
        );
    }

    public static int getSystemRelatedDimen(Context context, String key) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(key, "dimen", "android");
        return (resourceId > 0) ?
                resources.getDimensionPixelSize(resourceId)
                : 0;
    }

    public static void setTopBottomPadding(View view) {
        view.setPadding(
                0,
                getStatusBarHeight(view.getContext()),
                0,
                getNavigationBarHeight(view.getContext())
        );
    }

    public static void setTopPadding(View view) {
        view.setPadding(0, getStatusBarHeight(view.getContext()), 0, 0);
    }

    public static void setBottomPadding(View view) {
        view.setPadding(0, 0, 0, getNavigationBarHeight(view.getContext()));
    }
}
