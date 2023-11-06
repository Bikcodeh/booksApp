package com.bikcode.booksapp.ui.utils.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.viewModelScope(dispatcher: CoroutineDispatcher, block: suspend () -> Unit): Job {
    return viewModelScope.launch(dispatcher) { block() }
}