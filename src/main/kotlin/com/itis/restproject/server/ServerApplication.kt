package com.itis.restproject.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.context.annotation.ApplicationScope


@SpringBootApplication
class ServerApplication {

    @ApplicationScope
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}