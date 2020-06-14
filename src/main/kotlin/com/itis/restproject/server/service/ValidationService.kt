package com.itis.restproject.server.service

interface ValidationService {
    fun validateEmail(str: String): Boolean
    fun validatePassword(str: String): Boolean
    fun validateUsername(str: String): Boolean
}
