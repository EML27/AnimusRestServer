package com.itis.restproject.server.security.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.GenericFilterBean
import java.util.*


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authenticationProvider: AuthenticationProvider

    @Autowired
    @Qualifier(value = "jwtAuthenticationFilter")
    lateinit var jwtAuthenticationFilter: GenericFilterBean

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/signIn")
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.formLogin().disable()
        http.logout().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)
    }

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        val methodsAllowed: MutableList<String> = ArrayList()
        methodsAllowed.add("HEAD")
        methodsAllowed.add("GET")
        methodsAllowed.add("POST")
        methodsAllowed.add("PUT")
        methodsAllowed.add("DELETE")
        methodsAllowed.add("PATCH")
        configuration.allowedMethods = methodsAllowed

        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        val headersAllowed: MutableList<String> = ArrayList()
        headersAllowed.add("*")
        configuration.allowedHeaders = headersAllowed
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}
