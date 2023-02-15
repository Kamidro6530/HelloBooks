package com.example.hellobooks.screens.find_book_screen

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
class FindBookViewModel @Inject constructor(
    private val bookRepository: IBookRepository,
    val converters: Converters
) : ViewModel() {

    private var _searchBarResultsList : MutableState<List<Book>> = mutableStateOf(ArrayList())
    val searchBarResultsList = _searchBarResultsList

    suspend fun getBookFromBookService(query_parameter: String) =
        viewModelScope.launch {
            bookRepository.getBookFromBooksService(query_parameter).collect {
                if (it != null)
                    _searchBarResultsList.value = it
            }
        }



    sealed interface FindBookViewModelUiState {
        data class Success(val data : List<Book>) : FindBookViewModelUiState
        object Loading : FindBookViewModelUiState
        data class Error(val message : String) : FindBookViewModelUiState
    }

}