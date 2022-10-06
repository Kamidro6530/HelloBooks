package com.example.hellobooks.mvvm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.remote.dto.VolumeInfo
import com.example.hellobooks.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    val converters: Converters
) : ViewModel() {

    private var _listOfBooks = mutableStateListOf<Book>()
    val listOfBooks = _listOfBooks

    private var _searchBarResultsList = MutableStateFlow(listOf<Book?>())
    val searchBarResultsList = _searchBarResultsList


    init {
        viewModelScope.launch {
            _listOfBooks.addAll(bookRepository.getAllBooksFromDatabase().await())

        }
    }



    fun insertBook(book: Book) {
        viewModelScope.launch { bookRepository.insertBook(book) }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch { bookRepository.deleteBook(book) }
    }

    suspend fun getBook(query_parameter: String) {

        viewModelScope.launch {
            bookRepository.getBook(query_parameter).collect {
                if (it != null)
                    _searchBarResultsList.value = it

            }
        }

    }


}