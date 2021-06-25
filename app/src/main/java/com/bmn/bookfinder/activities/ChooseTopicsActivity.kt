package com.bmn.bookfinder.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bmn.bookfinder.R
import com.bmn.bookfinder.adapters.ChooseTopicsLayoutAdapter
import com.bmn.bookfinder.data.network.remote.ApiFunctions
import com.bmn.bookfinder.data.network.remote.ApiInterfaces.OnApiResponse
import com.bmn.bookfinder.data.room.AppDatabase
import com.bmn.bookfinder.data.room.BookEntity
import com.bmn.bookfinder.data.room.DatabaseAsync
import com.bmn.bookfinder.databinding.ActivityChooseTopicsBinding
import com.bmn.bookfinder.dummydata.DummyData
import com.bmn.bookfinder.helpers.SharedPrefUtils
import com.bmn.bookfinder.models.ApiResponse
import com.bmn.bookfinder.models.Topic
import com.bmn.bookfinder.models.googlebooks.GBResponse
import com.bmn.bookfinder.models.googlebooks.ResponseItem
import com.bmn.bookfinder.utils.AppUtils.isInternetAvailable
import com.bmn.bookfinder.utils.setTopBottomPadding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class ChooseTopicsActivity : AppCompatActivity(), View.OnClickListener, OnApiResponse {
    private var mApiFunctions: ApiFunctions? = null
    private var selectedTopics: ArrayList<Topic>? = null
    private var currentIndex = 0
    private var appDatabase: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChooseTopicsBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        appDatabase = AppDatabase.getDatabase(this)
        binding.topicsGrid.layoutManager = GridLayoutManager(applicationContext, 3)
        binding.root.setTopBottomPadding()
        val chooseTopicsLayoutAdapter = ChooseTopicsLayoutAdapter(
            applicationContext, DummyData.firstUseTopics
        )
        binding.topicsGrid.adapter = chooseTopicsLayoutAdapter
        binding.applyTopicsButton.setOnClickListener(this)
        mApiFunctions = ApiFunctions()
        mApiFunctions!!.setApiGenresResponseListener(this)
    }

    override fun onClick(v: View) {
        if (SharedPrefUtils.getFavoriteIds(applicationContext).isNotEmpty()) {
            selectedTopics = getSelectedTopics()
            if (applicationContext.isInternetAvailable()) {
                showAlertDialog()
                mApiFunctions!!.getBooksBySubject(
                    applicationContext,
                    selectedTopics!![currentIndex].text
                )
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    R.string.internet_connection_message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        } else {
            Snackbar.make(
                findViewById(android.R.id.content),
                R.string.please_choose_topics,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun showAlertDialog() {
        val view = layoutInflater.inflate(R.layout.choose_topic_custom_alert, null, false)
        val alertDialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(view)
        alertDialog = builder.create()
        val window = alertDialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    private fun getSelectedTopics(): ArrayList<Topic> {
        val allTopics = DummyData.firstUseTopics
        val selectedTopics = ArrayList<Topic>()
        val ids = SharedPrefUtils.getFavoriteIds(applicationContext)
        for (topic in allTopics) {
            if (ids.contains(topic.id)) {
                selectedTopics.add(topic)
            }
        }
        return selectedTopics
    }

    override fun onApiResponseCallback(status: Boolean, apiResponse: ApiResponse, message: String) {
        if (status) {
            val bookEntities = ArrayList<BookEntity>()
            val gbResponse = apiResponse as GBResponse
            if (gbResponse != null) {
                for (item in gbResponse.items) {
                    if (item.volumeInfo.imageLinks != null) {
                        bookEntities.add(getBookFromNetwork(item))
                    }
                }
                val databaseAsync = DatabaseAsync(appDatabase!!.bookDao) { success: Boolean ->
                    currentIndex++
                    if (currentIndex < selectedTopics!!.size) {
                        nextTopicBooks
                    } else {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        SharedPrefUtils.storeFirstTimeUsedTimestamp(
                            applicationContext,
                            System.currentTimeMillis()
                        )
                    }
                }
//                databaseAsync.execute(bookEntities!!)
            }
        }
    }

    private val nextTopicBooks: Unit
        get() {
            if (applicationContext.isInternetAvailable()) {
                mApiFunctions!!.getBooksBySubject(
                    applicationContext,
                    selectedTopics!![currentIndex].text
                )
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    R.string.internet_connection_message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

    private fun getBookFromNetwork(item: ResponseItem): BookEntity {
        val info = item.volumeInfo
        return BookEntity(
            item.id,
            selectedTopics!![currentIndex].id?.toLong() ?: 0L,
            selectedTopics!![currentIndex].text,
            info.title,
            info.description,
            info.imageLinks.thumbnail,
            info.authors,
            info.averageRating,
            info.pageCount,
            info.publishedDate,
            false
        )
    }
}