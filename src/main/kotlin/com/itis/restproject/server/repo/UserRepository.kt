package com.itis.restproject.server.repo

import com.itis.restproject.server.model.User
import org.springframework.data.jpa.repository.JpaRepository

import java.util.*

interface UserRepository: JpaRepository<User, Int> {
    fun findUserByEmail(email: String): Optional<User>

}