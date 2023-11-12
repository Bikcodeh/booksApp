package com.bikcode.booksapp.core.eventbus.events

data class HideBottomBarEvent(val show: Boolean = false): EventBusEvent()
