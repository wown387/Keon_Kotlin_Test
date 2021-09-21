package com.keonkotlin.keon_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class KeonKotlinApplication

fun main(args: Array<String>) {
	runApplication<KeonKotlinApplication>(*args)
}
