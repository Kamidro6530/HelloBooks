package com.example.hellobooks.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.leanback.widget.SearchBar
import com.example.hellobooks.ui.theme.background
import com.example.hellobooks.ui.theme.darkgreybackground
import com.example.hellobooks.ui.theme.primary

@ExperimentalComposeUiApi
@Composable
fun FindBookScreen() {
   SearchBar()

   ShowResults()
}

@Composable
fun ShowResults() {

}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun SearchBar(
   placeholderText: String = "",
) {
   var showClearButton by remember { mutableStateOf(false) }
   val keyboardController = LocalSoftwareKeyboardController.current

   var searchText by remember { mutableStateOf("") }


   TopAppBar(title = { Text("") }, navigationIcon = {
         Icon(
            imageVector = Icons.Filled.Search,
            modifier = Modifier.padding(start = 3.dp),
            contentDescription = "Search Icon"
         )

   }, actions = {

      OutlinedTextField(
         modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .onFocusChanged { focusState ->
               showClearButton = (focusState.isFocused)
            },
         value = searchText,
         onValueChange = { searchText = it },
         placeholder = {
            Text(text = placeholderText)
         },
         colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = background,
            unfocusedIndicatorColor = background,
            containerColor = background,
            cursorColor = primary
         ),
         trailingIcon = {
            AnimatedVisibility(
               visible = showClearButton,
               enter = fadeIn(),
               exit = fadeOut()
            ) {
               IconButton(onClick = { searchText="" }) {
                  Icon(
                     imageVector = Icons.Filled.Close,
                     contentDescription = "Clear text"
                  )
               }

            }
         },
         maxLines = 1,
         singleLine = true,
         keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
         }),
      )


   }, backgroundColor = background)

}
