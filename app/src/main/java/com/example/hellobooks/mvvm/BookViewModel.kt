package com.example.hellobooks.mvvm

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.repository.BookRepository
import com.example.hellobooks.repository.IBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: IBookRepository,
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
           getAllBooksFromDatabase()
        }
    }

    private suspend fun getAllBooksFromDatabase(){
        bookRepository.getAllBooksFromDatabase().collect {
            println("getAllBooksStart")
            _listOfBooksForBookshelf.value = it.filter { it.itShouldBeOnWishList == false }
            _listOfBooksForWishList.value = it.filter { it.itShouldBeOnWishList == true }
        }
        println("Viewmodel finish add books to list")
    }

    // Przebudowac Viewmodel przy uzyciu stateflow

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

    sealed interface BookViewModelUiState {
        data class Success(val data : List<Book>) : BookViewModelUiState
        object Loading : BookViewModelUiState
        data class Error(val message : String) : BookViewModelUiState
    }

}