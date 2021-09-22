package com.keonkotlin.keon_kotlin.member

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
@Slf4j
class UserHandler {

    @Autowired
    private lateinit var userService: UserService

    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        return request.principal()
                .flatMap { principal -> Mono.just(principal.name.toLong()) }
                .flatMap { memberId -> Mono.just(userService.getById(memberId)) }
                .flatMap { result -> ok().body(fromValue(result)) }
                .switchIfEmpty(notFound().build())
    }
}