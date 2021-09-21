package com.keonkotlin.keon_kotlin.member

import com.keonkotlin.keon_kotlin.member.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

}