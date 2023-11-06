package com.bikcode.booksapp.domain.commons

interface BaseValidationUseCase<In, Out> {
    fun execute(input: In): Out
}