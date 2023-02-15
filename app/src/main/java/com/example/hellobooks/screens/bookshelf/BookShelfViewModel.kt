package com.example.hellobooks.screens.bookshelf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellobooks.converters.Converters
import com.example.hellobooks.repository.IBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookShelfViewModel @Inject constructor(
    private val bookRepository: IBookRepository,
    val converters: Converters
) : ViewModel() {

    private var _bookShelfUiState = MutableStateFlow(BookShelfViewModelUiState.Success(emptyList()))
    val bookShelfUiState : StateFlow<BookShelfViewModelUiState> = _bookShelfUiState

    init {
        viewModelScope.launch {
            getAllBooksFromDatabase()
        }
    }

    private suspend fun getAllBooksFromDatabase() {
        bookRepository.getAllBooksFromDatabase().collect {
            _bookShelfUiState.value = BookShelfViewModelUiState.Success(it.filter { !it.itShouldBeOnWishList })
        }
    }




}