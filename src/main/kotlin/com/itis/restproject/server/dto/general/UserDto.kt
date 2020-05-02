package com.itis.restproject.server.dto.general

import com.itis.restproject.server.model.User


data class UserDto(var id: Int, var name: String, var email: String) {


    companion object {
        fun createFromUser(user: User): UserDto {
            return UserDto(user.userId, user.userName, user.email)
        }

        fun createFromUser(users: List<User>): List<UserDto> {
            return users.map { user -> createFromUser(user) }
        }
    }
}