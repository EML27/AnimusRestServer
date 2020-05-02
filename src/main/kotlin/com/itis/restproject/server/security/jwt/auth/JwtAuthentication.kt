package com.itis.restproject.server.security.jwt.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class JwtAuthentication(var token: String) : Authentication {
    private var isAuthenticated = false

    // информация о пользователе
    private lateinit var userDetails: UserDetails
    fun setUserDetails(userDetails: UserDetails) {
        this.userDetails = userDetails
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return userDetails.authorities
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }

    override fun getName(): String = token


    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any = userDetails

    override fun isAuthenticated(): Boolean = isAuthenticated


    override fun getDetails(): Any {
        return userDetails
    }
}
