package com.itis.restproject.server.service

import com.itis.restproject.server.dto.general.SignUpDto
import com.itis.restproject.server.model.User

interface SignUpService {
    fun signUp(signUpDto: SignUpDto): User
}
