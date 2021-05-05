package com.challenge.adidas.common.errorhandling

import retrofit2.HttpException
import java.net.ConnectException

class ErrorParserImpl : ErrorParser {
    override fun parse(throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> return when (throwable.code()) {
                400 -> Error.NotFound.message
                408 -> Error.InternetProblem.message
                500 -> Error.InternalServerError.message
                else -> Error.GeneralError.message
            }
            is ConnectException -> Error.InternetProblem.message
            else -> Error.GeneralError.message
        }
    }
}