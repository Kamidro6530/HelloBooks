package com.example.hellobooks.screens.bookshelf

import com.example.hellobooks.local.room.book.Book

sealed interface BookShelfViewModelUiState {
    data class Success(val data: List<Book>) : BookShelfViewModelUiState
    object Loading : BookShelfViewModelUiState
    data class Error(val message: String) : BookShelfViewModelUiState
}