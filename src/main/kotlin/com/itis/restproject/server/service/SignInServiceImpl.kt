package com.itis.restproject.server.service

import com.itis.restproject.server.dto.general.SignInDto
import com.itis.restproject.server.dto.general.TokenDto
import com.itis.restproject.server.repo.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SignInServiceImpl : SignInService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Value("\${jwt.secret}")
    lateinit var secret: String

    override fun signIn(signInData: SignInDto): TokenDto {
        var userOptional = userRepository.findUserByEmail(signInData.email)

        if (userOptional.isPresent) {
            var user = userOptional.get()
            if (passwordEncoder.matches(signInData.password, user.passwordHash)) {
                val token = Jwts.builder()
                        .setSubject(user.userId.toString())
                        .claim("name", user.userName)
                        .claim("role", user.role.name)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact()
                return TokenDto(token)

            } else throw AccessDeniedException("Wrong email and/or password")
        }
        throw AccessDeniedException("User not found")
    }
}