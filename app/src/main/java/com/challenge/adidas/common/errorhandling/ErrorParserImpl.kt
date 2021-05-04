package com.challenge.adidas.common.errorhandling

import retrofit2.HttpException

class ErrorParserImpl : ErrorParser {
    override fun parse(throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> return when (throwable.code()) {
                400 -> Error.NotFound.message
                408 -> Error.SocketTimeOut.message
                500 -> Error.InternalServerError.message
                else -> Error.GeneralError.message
            }
            else -> Error.GeneralError.message
        }
    }
}