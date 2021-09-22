package com.keonkotlin.keon_kotlin.common.exceptions

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class KeonException: RuntimeException {

    var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    var errorMessageCode = ErrorMessageCode.ERROR
    var errors: List<KeonError> = mutableListOf()

    constructor(message: String): super(message) {}
    constructor(message: String, httpStatus: HttpStatus): super(message) {
        this.httpStatus = httpStatus
    }
    constructor(message: String, httpStatus: HttpStatus, errorMessageCode: ErrorMessageCode): super(message) {
        this.httpStatus = httpStatus
        this.errorMessageCode = errorMessageCode
    }
    constructor(message: String, errorMessageCode: ErrorMessageCode): super(message) {
        this.errorMessageCode = errorMessageCode
    }
    constructor(errorMessageCode: ErrorMessageCode) {
        this.errorMessageCode = errorMessageCode
    }

    fun getKeonHttpStatus(): HttpStatus {
        return this.httpStatus
    }

    fun getKeonErrorMessageCode(): ErrorMessageCode {
        return this.errorMessageCode
    }

    fun getKeonErrors(): List<KeonError> {
        return this.errors
    }

    fun setKeonErrors(errors: List<KeonError>) {
        this.errors = errors
    }
}