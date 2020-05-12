package com.itis.restproject.server.service

import com.itis.restproject.server.dto.general.UserDto

interface UsersService {
    fun getAllUsers(): List<UserDto>

    fun deleteUser(userId: Int)

    fun getUserById(id: Int): UserDto
}
