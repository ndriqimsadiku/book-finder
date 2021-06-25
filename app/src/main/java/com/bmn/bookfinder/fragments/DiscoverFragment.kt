package com.bmn.bookfinder.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bmn.bookfinder.R
import com.bmn.bookfinder.adapters.TopicBooksAdapter
import com.bmn.bookfinder.adapters.TopicBooksAdapter.OnItemTopicBookClick
import com.bmn.bookfinder.data.network.remote.ApiFunctions
import com.bmn.bookfinder.data.network.remote.ApiInterfaces.OnApiResponse
import com.bmn.bookfinder.data.room.AppDatabase.Companion.getDatabase
import com.bmn.bookfinder.data.room.BookEntity
import com.bmn.bookfinder.databinding.FragmentDiscoverBinding
import com.bmn.bookfinder.models.ApiResponse
import com.bmn.bookfinder.models.googlebooks.GBResponse
import com.bmn.bookfinder.models.googlebooks.ResponseItem
import java.util.*

class DiscoverFragment : Fragment(), OnApiResponse<ApiResponse>, OnItemTopicBookClick {

    private lateinit var binding: FragmentDiscoverBinding
    private var mApiFunctions: ApiFunctions? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        mApiFunctions = ApiFunctions()
        mApiFunctions!!.setApiGenresResponseListener(this)
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage(getString(R.string.searching))
        progressDialog!!.setCancelable(false)
        binding.iconSearch.setOnClickListener { v: View? ->
            if (binding.searchEditText.text.toString().isEmpty()) {
                binding.searchEditText.requestFocus()
                binding.searchEditText.error = getString(R.string.type_something)
                return@setOnClickListener
            }
            progressDialog!!.show()
            mApiFunctions!!.searchBooks(context, binding.searchEditText.text.toString())
        }
    }

    override fun onApiResponseCallback(status: Boolean, apiResponse: ApiResponse?, message: String) {
        progressDialog?.cancel()
        if (status) {
            val bookEntities = mutableListOf<BookEntity>()
            val gbResponse = apiResponse as GBResponse
            for (item in gbResponse.items) {
                if (item.volumeInfo?.imageLinks != null) {
                    val entity = getBookFromNetwork(item)
                    entity?.let {
                        bookEntities.add(it)
                    }
                }
            }
            val booksAdapter = TopicBooksAdapter(requireContext())
            booksAdapter.setListener(this)
            booksAdapter.setBooks(bookEntities)
            getDatabase(context).bookDao.insertBooks(bookEntities)
            binding.searchResultsRv.adapter = booksAdapter
        }
    }

    private fun getBookFromNetwork(item: ResponseItem): BookEntity? {
        val info = item.volumeInfo
        if (info != null) {
            return BookEntity(
                item.id ?: "",
                0,
                "General",
                info.title ?: "",
                info.description ?: "",
                info.imageLinks?.thumbnail ?: "",
                info.authors ?: emptyList(),
                info.averageRating,
                info.pageCount,
                info.publishedDate ?: "",
                false
            )
        }
        return null
    }

    override fun onItemTopicBookClick(view: View, bookId: String) {
        val action = DiscoverFragmentDirections.actionDiscoverFragmentToBookActivity(bookId)
        Navigation.findNavController(requireView()).navigate(action)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}