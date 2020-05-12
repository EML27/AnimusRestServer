package com.itis.restproject.server.service

import com.itis.restproject.server.dto.general.UserDto
import com.itis.restproject.server.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UsersServiceImpl : UsersService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun getAllUsers(): List<UserDto> {
        return UserDto.createFromUser(userRepository.findAll())
    }

    override fun deleteUser(userId: Int) {
        userRepository.deleteById(userId)
    }

    override fun getUserById(id: Int): UserDto {
        return UserDto.createFromUser(userRepository.findUserByUserId(id).get())
    }
}
