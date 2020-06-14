package com.itis.restproject.server.security.jwt.details

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


class UserDetailsImpl (var userId: Int, var role: String, var name: String): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        val authority = SimpleGrantedAuthority(role)
        return Collections.singletonList(authority)
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String? {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
