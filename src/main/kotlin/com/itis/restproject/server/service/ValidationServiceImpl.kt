package com.itis.restproject.server.service

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class ValidationServiceImpl : ValidationService {
    override fun validateEmail(str: String): Boolean {
        val pattern = Pattern.compile("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    override fun validatePassword(str: String): Boolean {
        val pattern = Pattern.compile("[\\w]{6,45}")
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    override fun validateUsername(str: String): Boolean {
        val pattern = Pattern.compile("([0-9A-Za-z]{2,18})")
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }
}
