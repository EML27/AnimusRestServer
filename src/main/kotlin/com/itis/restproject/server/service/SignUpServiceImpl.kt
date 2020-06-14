package com.itis.restproject.server.service

import com.itis.restproject.server.dto.general.SignUpDto
import com.itis.restproject.server.model.Role
import com.itis.restproject.server.model.User
import com.itis.restproject.server.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SignUpServiceImpl : SignUpService {

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var mailService: MailService

    override fun signUp(signUpDto: SignUpDto): User {
        val user = User().apply {
            userName = signUpDto.username
            email = signUpDto.email
            passwordHash = passwordEncoder.encode(signUpDto.password)
            role = Role.USER
            activationCode = UUID.randomUUID().toString()
        }
        userRepository.save(user)
        val message = String.format(
                "Че, анимешник? Красава! \n" +
                        "Анимус ждет тебя! Чтобы активировать свой аккаунт, тебе нужно пройти по ссыл очке:\n" +
                        "http://localhost:80/auth/${user.activationCode}\n" +
                        "Скорее приходи, братик!"
        )
        mailService.send(user.email, "Активация аккаунта!", message)
        return user
    }
}
