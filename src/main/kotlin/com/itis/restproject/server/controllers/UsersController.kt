package com.itis.restproject.server.controllers

import com.itis.restproject.server.dto.general.UserDto
import com.itis.restproject.server.service.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController {
    @Autowired
    private lateinit var usersService: UsersService

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(usersService.getAllUsers())
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{user-id}")
    fun deleteUser(@PathVariable("user-id") userId: Int): ResponseEntity<Any?> {
        usersService.deleteUser(userId)
        return ResponseEntity.accepted().build()
    }

}