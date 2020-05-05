package com.itis.restproject.server.aspects

import com.itis.restproject.server.dto.general.SignInDto
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Slf4j
@Aspect
@Component
class LogAspect {
    private val log: Logger = LoggerFactory.getLogger(LogAspect::class.java)

    @Before(value = "execution(* com.itis.restproject.server.service.SignInService.signIn(*))")
    fun before(joinPoint: JoinPoint) {
        val args = joinPoint.args
        val signInDto = args[0] as SignInDto
        log.info("User ${signInDto.email} tries to authenticate")
    }

    @After(value = "execution(* com.itis.restproject.server.service.SignInService.signIn(*))")
    fun after(joinPoint: JoinPoint) {
        val args = joinPoint.args
        val signInDto = args[0] as SignInDto
        log.info("User with email ${signInDto.email} authenticated")
    }
}