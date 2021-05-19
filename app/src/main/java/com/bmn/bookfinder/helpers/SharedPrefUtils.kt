package com.bmn.bookfinder.helpers

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object SharedPrefUtils {
    private var sharedPreferences: SharedPreferences? = null
    fun storeFirstTimeUsedTimestamp(context: Context, timestamp: Long) {
        sharedPreferences =
            context.getSharedPreferences(SharedPrefs.FIRST_TIME_USED, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putLong(SharedPrefs.FIRST_TIME_USED_VALUE, timestamp)
        editor?.apply()
    }

    fun loadFirstTimeUsedTimestamp(context: Context): Long {
        sharedPreferences =
            context.getSharedPreferences(SharedPrefs.FIRST_TIME_USED, Context.MODE_PRIVATE)
        return sharedPreferences?.getLong(SharedPrefs.FIRST_TIME_USED_VALUE, 0L) ?: 0L
    }

    fun storeFavoriteIds(context: Context, ids: ArrayList<Int?>?) {
        val preferences = context.getSharedPreferences(
            SharedPrefs.SELECTED_TOPICS,
            Context.MODE_PRIVATE
        )
        val gson = Gson()
        val json = gson.toJson(ids)
        val editor = preferences.edit()
        editor.putString(SharedPrefs.SELECTED_TOPICS_VALUE, json)
        editor.apply()
    }

    @JvmStatic
    fun getFavoriteIds(context: Context): ArrayList<Int?> {
        val preferences = context.getSharedPreferences(
            SharedPrefs.SELECTED_TOPICS,
            Context.MODE_PRIVATE
        )
        val gson = Gson()
        val json = preferences.getString(SharedPrefs.SELECTED_TOPICS_VALUE, "")
        val type = object : TypeToken<List<Int?>?>() {}.type
        var items = ArrayList<Int?>()
        if (json?.isNotEmpty() == true) {
            items = gson.fromJson(
                json,
                type
            )
        }
        return items
    }

    fun addFavoriteId(context: Context, id: Int?) {
        val ids = getFavoriteIds(context)
        ids.add(id)
        storeFavoriteIds(context, ids)
    }

    fun removeFavoriteId(context: Context, id: Int?) {
        val ids = getFavoriteIds(context)
        if (ids.isNotEmpty() && ids.contains(id)) {
            ids.remove(id)
            storeFavoriteIds(context, ids)
        }
    }
}