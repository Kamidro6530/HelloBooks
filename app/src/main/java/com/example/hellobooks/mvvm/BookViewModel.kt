package com.example.hellobooks.mvvm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    val converters: Converters
) : ViewModel() {

    private var _listOfBooksForBookshelf : MutableState<List<Book>> = mutableStateOf(ArrayList())
    val listOfBooksForBookshelf = _listOfBooksForBookshelf

    private var _listOfBooksForWishList : MutableState<List<Book>> = mutableStateOf(ArrayList())
    val listOfBooksForWishList = _listOfBooksForWishList

    private var _searchBarResultsList : MutableState<List<Book>> = mutableStateOf(ArrayList())
    val searchBarResultsList = _searchBarResultsList


    init {
        viewModelScope.launch {
            bookRepository.getAllBooksFromDatabase().collect {
                _listOfBooksForBookshelf.value = it.filter { it.wishList == false }
                _listOfBooksForWishList.value = it.filter { it.wishList == true }
            }
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