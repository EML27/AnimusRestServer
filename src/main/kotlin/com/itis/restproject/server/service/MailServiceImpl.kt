package com.itis.restproject.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailServiceImpl : MailService {
    @Autowired
    private lateinit var mailSender: JavaMailSender

    @Value("\${spring.mail.username}")
    private lateinit var username: String

    override fun send(emailTo: String, subject: String, message: String) {
        var mailMessage = SimpleMailMessage()

        mailMessage.setFrom(username)
        mailMessage.setTo(emailTo)
        mailMessage.setSubject(subject)
        mailMessage.setText(message)

        mailSender.send(mailMessage)

    }
}
