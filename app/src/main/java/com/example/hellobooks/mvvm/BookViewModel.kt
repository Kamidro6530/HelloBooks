package com.example.hellobooks.mvvm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellobooks.room.book.Book
import com.example.hellobooks.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor( val bookRepository: BookRepository) : ViewModel() {

    private var _listOfBooks = mutableStateListOf<Book>()
    val listOfBooks  = _listOfBooks


    init {
        viewModelScope.launch {
           _listOfBooks.addAll( bookRepository.getAllBooks().await() )

        }
    }

    suspend fun insertBook(book: Book) {
        viewModelScope.launch { bookRepository.insertBook(book)  }
    }

    suspend  fun deleteBook(book: Book){
        viewModelScope.launch { bookRepository.deleteBook(book) }
    }




}