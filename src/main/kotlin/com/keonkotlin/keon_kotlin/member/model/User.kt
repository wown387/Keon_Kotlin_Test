package com.keonkotlin.keon_kotlin.member.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.keonkotlin.keon_kotlin.common.model.BaseEntity
import lombok.Data
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Data
class User: BaseEntity() {

    @Column
    var name = ""

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""
        @JsonIgnore
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

}