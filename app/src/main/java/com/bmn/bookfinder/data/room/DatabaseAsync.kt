package com.bmn.bookfinder.data.room

import android.os.AsyncTask
import java.util.*

/**
 * Created by Ndriçim Sadiku on 30 June 2020
 * ndricim@frakton.com
 */
class DatabaseAsync(private val bookDao: BookDao, private val listener: (Boolean) -> Unit) :
    AsyncTask<ArrayList<BookEntity?>?, Long?, Boolean>() {
    override fun doInBackground(vararg params: ArrayList<BookEntity?>?): Boolean {
        bookDao.insertBooks(params[0])
        return true
    }

    override fun onPostExecute(aBoolean: Boolean) {
        super.onPostExecute(aBoolean)
        listener(aBoolean)
    }
}