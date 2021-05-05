package com.challenge.adidas.common.errorhandling

sealed class Error {
    abstract val message: String

    object NotFound : Error() {
        override val message: String
            get() = "There is a problem please try later"
    }

    object InternetProblem : Error() {
        override val message: String
            get() = "Please check your internet"
    }

    object InternalServerError : Error() {
        override val message: String
            get() = "There is a problem please try later"
    }

    object GeneralError : Error(){
        override val message: String
            get() = "Try again"
    }


}