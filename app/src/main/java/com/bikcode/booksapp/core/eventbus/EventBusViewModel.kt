package com.bikcode.booksapp.core.eventbus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikcode.booksapp.core.eventbus.events.EventBusEvent
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventBusViewModel @Inject constructor(
    private val eventBus: AppEventBus,
    private val dispatcherProvider: DispatcherProvider
): ViewModel() {
    fun getEvents() = eventBus.events

    fun sendEvent(event: EventBusEvent) {
        viewModelScope.launch(dispatcherProvider.io) {
            eventBus.emit(event)
        }
    }
}