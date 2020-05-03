package com.itis.restproject.server.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Int = 0

    @Column(unique = true)
    lateinit var email: String

    @Column(unique = true)
    lateinit var userName: String
    lateinit var passwordHash: String

    @Enumerated(value = EnumType.STRING)
    lateinit var role: Role

}




