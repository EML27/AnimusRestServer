package com.itis.restproject.server.model

import lombok.*
import javax.persistence.*

@Entity
@Builder
@NoArgsConstructor
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Int,
        val email: String,
        val userName: String,
        val passwordHash: String,
        @Enumerated(value = EnumType.STRING)
        val role: Role
)





