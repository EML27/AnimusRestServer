package com.itis.restproject.server.security.jwt.filter

import com.itis.restproject.server.security.jwt.auth.JwtAuthentication
import org.springframework.context.annotation.Scope
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Scope(scopeName = "authentication")
@Component("jwtAuthenticationFilter")
class JwtAuthenticationFilter : GenericFilterBean()  {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse?,
                          filterChain: FilterChain) {
        // преобразуем запрос в HTTP
        val request = servletRequest as HttpServletRequest
        // получаем токен
        val token = request.getHeader("Authorization")
        // создаем объект аутентификации
        val authentication: Authentication = JwtAuthentication(token)
        // кладем его в контекст для текущего потока
        SecurityContextHolder.getContext().authentication = authentication
        // отправили запрос дальше
        filterChain.doFilter(servletRequest, servletResponse)
    }

}