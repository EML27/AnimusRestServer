package com.itis.restproject.server.scopes.configs

import com.itis.restproject.server.scopes.AuthenticationScope
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory

class AuthenticationBeanFactoryPostProcessor: BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        beanFactory.registerScope("authentication", AuthenticationScope())
    }
}