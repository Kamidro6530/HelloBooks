package com.example.hellobooks.screens.bookshelf

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.local.room.book.Book
import com.example.hellobooks.repository.IBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookShelfViewModel @Inject constructor(
    private val bookRepository: IBookRepository,
    val converters: Converters
) : ViewModel() {

    private var _listOfBooksForBookshelf: MutableState<List<Book>> = mutableStateOf(ArrayList())
    val listOfBooksForBookshelf = _listOfBooksForBookshelf

    init {
        viewModelScope.launch {
            getAllBooksFromDatabase()
        }
    }

    private suspend fun getAllBooksFromDatabase() {
        bookRepository.getAllBooksFromDatabase().collect {
            _listOfBooksForBookshelf.value = it.filter { it.itShouldBeOnWishList == false }
        }
    }


    sealed interface BookShelfViewModelUiState {
        data class Success(val data: List<Book>) : BookShelfViewModelUiState
        object Loading : BookShelfViewModelUiState
        data class Error(val message: String) : BookShelfViewModelUiState
    }

}