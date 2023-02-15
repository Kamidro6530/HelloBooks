package com.example.hellobooks.screens.wish_list

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
class WishListViewModel @Inject constructor(
    private val bookRepository : IBookRepository,
    val converters: Converters
) : ViewModel() {

    private var _listOfBooksForWishList : MutableState<List<Book>> = mutableStateOf(ArrayList())
    val listOfBooksForWishList = _listOfBooksForWishList

    init {
        viewModelScope.launch {
            getAllBooksFromDatabase()
        }
    }

    private suspend fun getAllBooksFromDatabase(){
        bookRepository.getAllBooksFromDatabase().collect {
            _listOfBooksForWishList.value = it.filter { it.itShouldBeOnWishList == true }
        }
    }

    sealed interface WishListViewModelUiState {
        data class Success(val data : List<Book>) : WishListViewModelUiState
        object Loading : WishListViewModelUiState
        data class Error(val message : String) : WishListViewModelUiState
    }


}