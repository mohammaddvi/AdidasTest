package com.challenge.adidas.common.errorhandling

interface ErrorParser {
    fun parse(throwable: Throwable):String
}