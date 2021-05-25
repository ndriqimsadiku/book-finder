package com.bmn.bookfinder.utils

import android.content.Context
import android.content.res.Resources
import android.view.View
import kotlin.math.roundToInt

/**
 * Created by Ndriçim Sadiku on 22 June 2020
 * ndricim@frakton.com
 */

private const val STATUS_BAR_HEIGHT = "status_bar_height"
private const val NAVIGATION_BAR_HEIGHT = "navigation_bar_height"

fun Context.getStatusBarHeight(): Int = getSystemRelatedDimen(STATUS_BAR_HEIGHT)


fun Context.getNavigationBarHeight(): Int = getSystemRelatedDimen(NAVIGATION_BAR_HEIGHT)

fun Context.getSystemRelatedDimen(key: String?): Int {
    val resources = this.resources
    val resourceId = resources.getIdentifier(key, "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}

fun View.setTopBottomPadding() {
    this.setPadding(
        0,
        this.context.getStatusBarHeight(),
        0,
        this.context.getNavigationBarHeight()
    )
}

fun View.setTopPadding() {
    this.setPadding(0, this.context.getStatusBarHeight(), 0, 0)
}

fun View.setBottomPadding() {
    this.setPadding(0, 0, 0, this.context.getNavigationBarHeight())
}

fun convertDpToPixel(dp: Float): Int {
    val metrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / 160f)
    return px.roundToInt()
}
