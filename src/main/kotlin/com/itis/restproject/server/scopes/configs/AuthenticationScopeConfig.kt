package com.itis.restproject.server.scopes.configs

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthenticationScopeConfig {
    @Bean
    fun beanFactoryPostProcessor(): BeanFactoryPostProcessor = AuthenticationBeanFactoryPostProcessor()
}   