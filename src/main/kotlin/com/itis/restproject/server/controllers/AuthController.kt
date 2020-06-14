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
import com.itis.restproject.server.service.SignUpService
import com.itis.restproject.server.service.UsersService
import com.itis.restproject.server.service.ValidationService
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
    lateinit var signUpService: SignUpService

    @Autowired
    private lateinit var signInService: SignInService

    @Autowired
    private lateinit var usersService: UsersService

    @Autowired
    private lateinit var validationService: ValidationService

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
        if (signUpDto.email == "" || signUpDto.password == "" || signUpDto.username == "") {
            throw IllegalArgumentException("Fields must not be empty")
        }
        //Да, я отлично знаю что так делать нельзя, и эта работа должна быть вынесена в сервис,
        // но почему-то абсолютно тот же самый код не работает в отдельном сервисе, хотя просто обязан.
        // Без понятия, что не так.
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

    @GetMapping("/{activationCode}")
    fun activate(@PathVariable activationCode: String): String {
        try {
            usersService.activateUser(activationCode)
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error\n" +
                    "$e"
        }
        return "Success! You are now activated!"
    }
}
