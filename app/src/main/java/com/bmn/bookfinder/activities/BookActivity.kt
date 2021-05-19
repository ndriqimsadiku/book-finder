package com.bmn.bookfinder.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bmn.bookfinder.R
import com.bmn.bookfinder.data.room.AppDatabase
import com.bmn.bookfinder.data.room.BookEntity
import com.bmn.bookfinder.databinding.ActivityBookBinding
import com.bumptech.glide.Glide

class BookActivity : AppCompatActivity() {
    private var binding: ActivityBookBinding? = null
    private var bookEntity: BookEntity? = null
    private var bookId: String? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        bookId = BookActivityArgs.fromBundle(intent.extras!!).bookId
        setBookData(bookId!!)
        binding!!.bookRating.setOnTouchListener { v: View?, event: MotionEvent? -> true }
        listenBookFavoriteChanges()
    }

    fun listenBookFavoriteChanges() {
        binding!!.favoriteBook.setOnClickListener { v: View? ->
            bookEntity!!.isFavorite = !bookEntity!!.isFavorite
            binding!!.favoriteBook.setImageDrawable(
                if (bookEntity!!.isFavorite) getDrawable(R.drawable.ic_heart_on) else getDrawable(R.drawable.ic_heart)
            )
            AppDatabase.getDatabase(applicationContext).bookDao.setBookAsFavoriteById(
                bookId,
                bookEntity!!.isFavorite
            )
        }
    }

    private fun setBookData(bookId: String) {
        bookEntity = AppDatabase.getDatabase(applicationContext).bookDao.getBookById(bookId)
        val bookEntity = bookEntity
        bookEntity ?: return
        formatAuthors(bookEntity.authors)
        binding!!.bookDescription.text = bookEntity.description
        binding!!.bookPageCount.text = getString(R.string.page_count_d, bookEntity.pageCount)
        binding!!.bookPublishedDate.text = bookEntity.publishedDate
        binding!!.bookTitle.text = bookEntity.title
        binding!!.bookRating.rating = bookEntity.averageRating.toFloat()
        binding!!.favoriteBook.setImageDrawable(
            if (bookEntity.isFavorite) getDrawable(R.drawable.ic_heart_on) else getDrawable(R.drawable.ic_heart)
        )
        Glide.with(this)
            .load(bookEntity.thumbnailUrl)
            .into(binding!!.bookImage)
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
            binding!!.bookAuthors.text = authorsBuilder.toString()
        } else {
            binding!!.bookAuthors.setText(R.string.anonymous)
        }
    }
}