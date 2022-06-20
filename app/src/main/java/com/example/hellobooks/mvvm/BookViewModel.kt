package com.example.hellobooks.mvvm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellobooks.room.book.Book
import com.example.hellobooks.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BookViewModel( val bookRepository: BookRepository) : ViewModel() {

    private var _listOfBooks = MutableStateFlow<List<Book>>(mutableStateListOf<Book>())
    val listOfBooks = _listOfBooks


    init {
        viewModelScope.launch {
            listOfBooks.value = bookRepository.getAllBooks().await()

        }
    }

    fun insertBook(book: Book) {
        viewModelScope.launch { bookRepository.insertBook(book)  }
    }

    fun deleteBook(book: Book){
        viewModelScope.launch { bookRepository.deleteBook(book) }
    }




}