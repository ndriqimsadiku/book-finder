package com.bmn.bookfinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bmn.bookfinder.adapters.FavoriteTopicsAdapter
import com.bmn.bookfinder.data.room.AppDatabase.Companion.getDatabase
import com.bmn.bookfinder.databinding.FragmentFavoritesBinding
import com.bmn.bookfinder.models.Topic
import java.util.*

class FavoritesFragment : Fragment() {
    private var topics: ArrayList<Topic>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoritesBinding.inflate(layoutInflater)
        init()
        val adapter =
            FavoriteTopicsAdapter { book ->
                val action =
                    FavoritesFragmentDirections.actionFavoritesFragmentToTopicFragment(
                        book.topicId,
                        topicTitle = book.title,
                    )
                findNavController().navigate(action)
            }
        val bookEntities = getDatabase(context).bookDao.favoriteTopics
        adapter.submitList(bookEntities)
        binding.favoritesList.adapter = adapter
        binding.listEmptyMessage.visibility =
            if (bookEntities != null && bookEntities.isNotEmpty()) View.GONE else View.VISIBLE
        return binding.root
    }

    private fun init() {
        topics = ArrayList()
    }
}