package com.bmn.bookfinder.fragments

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bmn.bookfinder.R
import com.bmn.bookfinder.data.room.AppDatabase
import com.bmn.bookfinder.data.room.BookEntity
import com.bmn.bookfinder.databinding.FragmentBookBinding
import com.bumptech.glide.Glide

class BookFragment : Fragment(R.layout.fragment_book) {
    private lateinit var binding: FragmentBookBinding
    private val args by navArgs<BookFragmentArgs>()
    private var bookEntity: BookEntity? = null
    private var bookId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBookBinding.bind(view)
        bookId = args.bookId
        setBookData(bookId!!)
        listenBookFavoriteChanges()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun listenBookFavoriteChanges() {
        binding.favoriteBook.setOnClickListener { v: View? ->
            bookEntity!!.isFavorite = !bookEntity!!.isFavorite
            binding.favoriteBook.setImageDrawable(
                if (bookEntity!!.isFavorite) getDrawable(
                    requireContext(),
                    R.drawable.ic_heart_on
                ) else getDrawable(requireContext(), R.drawable.ic_heart)
            )
            AppDatabase.getDatabase(requireContext()).bookDao.setBookAsFavoriteById(
                bookId,
                bookEntity!!.isFavorite
            )
        }
    }

    private fun setBookData(bookId: String) {
        bookEntity = AppDatabase.getDatabase(requireContext()).bookDao.getBookById(bookId)
        val bookEntity = bookEntity
        bookEntity ?: return
        formatAuthors(bookEntity.authors)
        binding.bookDescription.text = bookEntity.description
        binding.bookPageCount.text = getString(R.string.page_count_d, bookEntity.pageCount)
        binding.bookPublishedDate.text = bookEntity.publishedDate
        binding.bookTitle.text = bookEntity.title
        binding.bookRating.rating = bookEntity.averageRating.toFloat()
        binding.favoriteBook.setImageDrawable(
            if (bookEntity.isFavorite) getDrawable(requireContext(), R.drawable.ic_heart_on) else getDrawable(requireContext(),R.drawable.ic_heart)
        )
        Glide.with(this)
            .load(bookEntity.thumbnailUrl)
            .into(binding.bookImage)
    }

    private fun formatAuthors(authors: List<String>) {
        val authorsBuilder = StringBuilder()
        if (authors.isNotEmpty()) {
            for (author in authors) {
                if (authors.indexOf(author) == authors.size - 1) {
                    authorsBuilder.append(author)
                }
                authorsBuilder.append(String.format("%s, ", author))
            }
            binding.bookAuthors.text = authorsBuilder.toString()
        } else {
            binding.bookAuthors.setText(R.string.anonymous)
        }
    }
}