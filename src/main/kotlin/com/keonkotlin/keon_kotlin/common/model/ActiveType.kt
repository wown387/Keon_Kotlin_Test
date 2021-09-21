package com.keonkotlin.keon_kotlin.common.model

enum class ActiveType(var codeValue: Int) {
    DELETE(0) {
        override fun shout(): String {
            return "DELETE"
        }
    },
    ACTIVE(1) {
        override fun shout(): String {
            return "Active"
        }
    };
    fun getCode(): Int {
        return codeValue;
    }
    abstract fun shout(): String
}