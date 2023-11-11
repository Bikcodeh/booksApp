package com.bikcode.booksapp.core.eventbus.events

data class CategoryFab(val show: Boolean = true, val onClick: () -> Unit) : EventBusEvent()