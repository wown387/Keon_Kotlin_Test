package com.keonkotlin.keon_kotlin.member

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse


@Configuration
class UserRouter {

    @Bean
    fun userRouterFunction(handler: UserHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.nest(
                path("/api/user"),
                route(GET("").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
        )
    }
}