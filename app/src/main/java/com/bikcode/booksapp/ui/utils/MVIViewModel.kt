package com.bikcode.booksapp.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.utils.extension.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class MVIViewModel<
        Effect : ViewSideEffect,
        Event : ViewEvent
        >(private val dispatcher: DispatcherProvider) : ViewModel() {

    private val _events = Channel<Event>(Channel.UNLIMITED)

    val effects = Channel<Effect>(Channel.UNLIMITED)
    abstract fun handleEvents(event: Event)

    fun sendEvent(event: () -> Event) =
        viewModelScope(dispatcher.io) { _events.send(event()) }

    protected fun setEffect(builder: () -> Effect) =
        viewModelScope(dispatcher.io) { effects.send(builder()) }

    init {
        viewModelScope.launch(dispatcher.io) {
            _events.consumeAsFlow().collect { event ->
                handleEvents(event)
            }
        }
    }
}