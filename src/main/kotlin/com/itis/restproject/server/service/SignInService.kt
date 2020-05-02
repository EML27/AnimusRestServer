package com.itis.restproject.server.service

import com.itis.restproject.server.dto.general.SignInDto
import com.itis.restproject.server.dto.general.TokenDto

interface SignInService {
    fun signIn(signInData: SignInDto): TokenDto
}