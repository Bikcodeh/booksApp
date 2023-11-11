package com.bikcode.booksapp.core.eventbus

import com.bikcode.booksapp.core.eventbus.events.EventBusEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AppEventBus @Inject constructor() {
    private val _eventFlow = MutableSharedFlow<EventBusEvent>()

    val events: SharedFlow<EventBusEvent> = _eventFlow.asSharedFlow()
    fun subscribe(scope: CoroutineScope, block: suspend (EventBusEvent) -> Unit) = _eventFlow.onEach(block).launchIn(scope)
    suspend fun emit(appEvent: EventBusEvent) = _eventFlow.emit(appEvent)
}