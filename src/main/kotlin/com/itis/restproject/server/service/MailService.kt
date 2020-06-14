package com.itis.restproject.server.service

interface MailService {

    fun send(emailTo: String, subject: String, message: String)
}
