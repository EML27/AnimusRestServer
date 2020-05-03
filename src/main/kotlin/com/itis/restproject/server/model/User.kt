package com.itis.restproject.server.model

import lombok.Builder
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Builder
@NoArgsConstructor
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Int,
        @Column(unique = true)
        val email: String,
        @Column(unique = true)
        val userName: String,
        val passwordHash: String,
        @Enumerated(value = EnumType.STRING)
        val role: Role
)





