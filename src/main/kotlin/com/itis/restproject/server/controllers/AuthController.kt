package com.itis.restproject.server.controllers

import com.itis.restproject.server.dto.general.SignInDto
import com.itis.restproject.server.dto.general.SignUpDto
import com.itis.restproject.server.dto.general.TokenDto
import com.itis.restproject.server.dto.general.UserDto
import com.itis.restproject.server.model.Role
import com.itis.restproject.server.model.User
import com.itis.restproject.server.repo.UserRepository
import com.itis.restproject.server.security.jwt.details.UserDetailsImpl
import com.itis.restproject.server.service.SignInService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("auth")
class AuthController {

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var userRepository: UserRepository

    @CrossOrigin("*")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("self")
    fun getSelf(): ResponseEntity<UserDto>? {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val userDetails = authentication.details as UserDetailsImpl
        println(userDetails)
        return ResponseEntity.ok(UserDto().also {
            it.name = userDetails.name
            it.id = userDetails.userId
        })

    }

    @Autowired
    private lateinit var signInService: SignInService

    @CrossOrigin("*")
    @PostMapping("/signIn")
    fun signIn(@RequestBody signInData: SignInDto): ResponseEntity<TokenDto> {
        return ResponseEntity.ok(signInService.signIn(signInData))
    }

    @CrossOrigin("*")
    @GetMapping("/encode/{text}")
    fun encode(@PathVariable text: String): String {
        return passwordEncoder.encode(text)
    }

    @CrossOrigin("*")
    @PostMapping("/signUp")
    fun signUp(@RequestBody signUpDto: SignUpDto): ResponseEntity<TokenDto> {
        val user = User().apply {
            userName = signUpDto.username
            email = signUpDto.email
            passwordHash = passwordEncoder.encode(signUpDto.password)
            role = Role.USER
        }
        userRepository.save(user)
        val signInDto = SignInDto(signUpDto.email, signUpDto.password)
        return ResponseEntity.ok(signInService.signIn(signInDto))
    }
}
