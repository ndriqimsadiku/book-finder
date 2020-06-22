package com.bmn.bookfinder.utils

import android.content.Context
import android.view.View

/**
 * Created by Ndriçim Sadiku on 22 June 2020
 * ndricim@frakton.com
 */

private const val STATUS_BAR_HEIGHT = "status_bar_height"
private const val NAVIGATION_BAR_HEIGHT = "navigation_bar_height"

fun getStatusBarHeight(context: Context): Int {
    return getSystemRelatedDimen(context, STATUS_BAR_HEIGHT)
}

fun getNavigationBarHeight(context: Context): Int {
    return getSystemRelatedDimen(
        context,
        NAVIGATION_BAR_HEIGHT
    )
}

fun getSystemRelatedDimen(context: Context, key: String): Int {
    val resources = context.resources
    val resourceId = resources.getIdentifier(key, "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else 0
}

fun setTopBottomPadding(view: View) {
    view.setPadding(
        0,
        getStatusBarHeight(view.context),
        0,
        getNavigationBarHeight(view.context)
    )
}

fun setTopPadding(view: View) {
    view.setPadding(0, getStatusBarHeight(view.context), 0, 0)
}

fun setBottomPadding(view: View) {
    view.setPadding(0, 0, 0, getNavigationBarHeight(view.context))
}