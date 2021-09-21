package com.keonkotlin.keon_kotlin.common.model

import lombok.Data
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
@Data
open class BaseEntity : Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var activeType: ActiveType = ActiveType.ACTIVE
    var cratedAt: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    var createdId: Long = 0L
    var updatedAt: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    var updatedId: Long = 0L
}