package com.bikcode.booksapp.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.utils.extension.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class MVIViewModel<Event : ViewEvent> : ViewModel() {
    abstract fun handleEvents(event: Event)
}