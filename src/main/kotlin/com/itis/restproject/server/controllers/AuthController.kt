package com.itis.restproject.server.controllers

import com.itis.restproject.server.dto.general.SignInDto
import com.itis.restproject.server.dto.general.TokenDto
import com.itis.restproject.server.dto.general.UserDto
import com.itis.restproject.server.service.SignInService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
class AuthController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("self")
    fun getSelf(): ResponseEntity<UserDto>? {
        return null
    }

    @Autowired
    private lateinit var signInService: SignInService

    @PostMapping("/signIn")
    public fun signIn(@RequestBody signInData: SignInDto): ResponseEntity<TokenDto> {
        return ResponseEntity.ok(signInService.signIn(signInData))
    }
}