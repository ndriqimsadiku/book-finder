package com.bmn.bookfinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bmn.bookfinder.adapters.TopicBooksAdapter
import com.bmn.bookfinder.adapters.TopicBooksAdapter.OnItemTopicBookClick
import com.bmn.bookfinder.data.room.AppDatabase.Companion.getDatabase
import com.bmn.bookfinder.databinding.FragmentTopicBinding

/**
 * Created by Ndriçim Sadiku on 01 July 2020
 * ndricim@frakton.com
 */
class TopicFragment : Fragment(), OnItemTopicBookClick {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTopicBinding.inflate(layoutInflater)
        val args = TopicFragmentArgs.fromBundle(arguments)
        binding.topicTitle.text = args.topicTitle
        binding.back.setOnClickListener { requireActivity().onBackPressed() }
        val list = getDatabase(context).bookDao.getBookByTopicId(args.topicId)
        val booksAdapter = TopicBooksAdapter(requireContext())
        booksAdapter.setListener(this)
        booksAdapter.setBooks(list)
        binding.bookList.adapter = booksAdapter
        return binding.root
    }

    override fun onItemTopicBookClick(view: View, bookId: String) {
        val action = TopicFragmentDirections.actionTopicFragmentToBookActivity()
        action.bookId = bookId
        Navigation.findNavController(view).navigate(action)
    }
}