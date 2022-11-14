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
                _listOfBooksForBookshelf.value = it.filter { it.itShouldBeOnWishList == false }
                _listOfBooksForWishList.value = it.filter { it.itShouldBeOnWishList == true }
            }
        }
    }



    fun insertBookToDatabase(book: Book) {
        viewModelScope.launch { bookRepository.insertBookToDatabase(book) }
    }

    fun deleteBookFromDatabase(book: Book) {
        viewModelScope.launch { bookRepository.deleteBookFromDatabase(book) }
    }

    suspend fun getBookFromBookService(query_parameter: String) {

        viewModelScope.launch {
            bookRepository.getBookFromBooksService(query_parameter).collect {
                if (it != null)
                    _searchBarResultsList.value = it
            }
        }

    }


}