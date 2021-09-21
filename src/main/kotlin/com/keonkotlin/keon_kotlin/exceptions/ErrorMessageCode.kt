package com.keonkotlin.keon_kotlin.exceptions

enum class ErrorMessageCode(var codeValue: Int, var message: String) {
    SUCCESS(20000, "success"),
    ERROR(50000, "error");

    fun getCode(): Int {
        return codeValue
    }

    fun printMessage(): String {
        return message
    }
}