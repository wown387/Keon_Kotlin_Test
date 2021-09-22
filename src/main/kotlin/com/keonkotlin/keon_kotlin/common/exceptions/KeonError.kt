package com.keonkotlin.keon_kotlin.common.exceptions

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import java.io.Serializable

@Data
class KeonError @JsonCreator constructor(
        @JsonProperty("path") path: String,
        @JsonProperty("code") code: Int,
        @JsonProperty("message") message: String,
    ): Serializable {

        val path: String = path
        val code: Int = code
        val message: String = message
}