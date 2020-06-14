package com.itis.restproject.server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig {

    @Value("\${spring.mail.host}")
    private lateinit var host: String

    @Value("\${spring.mail.username}")
    private lateinit var username: String

    @Value("\${spring.mail.password}")
    private lateinit var password: String

    @Value("\${spring.mail.port}")
    private var port: Int = 0

    @Value("\${spring.mail.protocol}")
    private lateinit var protocol: String

    @Bean
    fun getMailSender(): JavaMailSender {
        val res = JavaMailSenderImpl()

        res.host = host
        res.username = username
        res.password = password
        res.port = port
        res.protocol = protocol

        val properties = res.javaMailProperties

        properties.setProperty("mail.transport.protocol", protocol)
        properties.setProperty("mail.debug", "true")

        return res
    }
}
