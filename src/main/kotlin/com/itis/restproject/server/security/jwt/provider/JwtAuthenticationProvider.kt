package com.itis.restproject.server.security.jwt.provider

import com.itis.restproject.server.security.jwt.auth.JwtAuthentication
import com.itis.restproject.server.security.jwt.details.UserDetailsImpl
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Scope
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import javax.naming.AuthenticationException

@Scope(scopeName = "authentication")
@Component
class JwtAuthenticationProvider : AuthenticationProvider {

    @Value("\${jwt.secret}")
    lateinit var secret: String

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
        val token: String? = authentication.name
        val claims: Claims
        claims = try {
            // выполняю парсинг токена со своим секретным ключом
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .body
        } catch (e: Exception) {
            throw AuthenticationCredentialsNotFoundException("Bad token")
        }
        // создаем UserDetails
        val userDetails: UserDetails = UserDetailsImpl(
                claims["sub", String::class.java].toInt(),
                claims["role", String::class.java],
                claims["name", String::class.java])
        // аутентификация прошла успешно
        authentication.setAuthenticated(true)
        // положили в эту аутентификацию пользователя
        (authentication as JwtAuthentication).setUserDetails(userDetails)
        return authentication
    }

    // проверяет, подходит ли текущий провайдер для этой аутентификации
    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthentication::class.java == authentication
    }

}