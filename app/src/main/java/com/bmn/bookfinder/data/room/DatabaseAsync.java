package com.bmn.bookfinder.data.room;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Ndriçim Sadiku on 30 June 2020
 * ndricim@frakton.com
 */
public class DatabaseAsync extends AsyncTask<ArrayList<BookEntity>, Long, Boolean> {

    private BookDao bookDao;
    private Listener listener;

    public DatabaseAsync(BookDao dao, Listener listener) {
        this.bookDao = dao;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(ArrayList<BookEntity>... arrayLists) {
        bookDao.insertBooks(arrayLists[0]);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        listener.onDone(aBoolean);
    }

   public interface Listener {
        void onDone(boolean success);
    }
}
