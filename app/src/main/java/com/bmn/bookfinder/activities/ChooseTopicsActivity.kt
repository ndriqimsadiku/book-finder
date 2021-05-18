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
import com.bmn.bookfinder.data.network.remote.ApiInterfaces.onApiResponse
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
import com.bmn.bookfinder.utils.AppUtils
import com.bmn.bookfinder.utils.DimenUtils
import com.google.android.material.snackbar.Snackbar
import java.util.*

class ChooseTopicsActivity : AppCompatActivity(), View.OnClickListener, onApiResponse {
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
        DimenUtils.setTopBottomPadding(binding.root)
        val chooseTopicsLayoutAdapter = ChooseTopicsLayoutAdapter(
            applicationContext, DummyData.getFirstUseTopics()
        )
        binding.topicsGrid.adapter = chooseTopicsLayoutAdapter
        binding.applyTopicsButton.setOnClickListener(this)
        mApiFunctions = ApiFunctions()
        mApiFunctions!!.setApiGenresResponseListener(this)
    }

    override fun onClick(v: View) {
        if (!SharedPrefUtils.getFavoriteIds(applicationContext).isEmpty()) {
            selectedTopics = getSelectedTopics()
            if (AppUtils.isInternetAvailable(applicationContext)) {
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
        val allTopics = DummyData.getFirstUseTopics()
        val selectedTopics = ArrayList<Topic>()
        val ids = SharedPrefUtils.getFavoriteIds(applicationContext)
        for (topic in allTopics) {
            if (ids.contains(topic.id)) {
                selectedTopics.add(topic)
            }
        }
        return selectedTopics
    }

    override fun onApiResponse(status: Boolean, apiResponse: ApiResponse, message: String) {
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
                databaseAsync.execute(bookEntities)
            }
        }
    }

    private val nextTopicBooks: Unit
        private get() {
            if (AppUtils.isInternetAvailable(applicationContext)) {
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
            selectedTopics!![currentIndex].id.toLong(),
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