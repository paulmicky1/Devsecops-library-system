package com.ssd.minilibrary.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@Configuration
public class SecurityEventsConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger("com.ssd.minilibrary.security");

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessLogger() {
        return event -> LOGGER.info("Login success for user={}", event.getAuthentication().getName());
    }

    @Bean
    public ApplicationListener<AbstractAuthenticationFailureEvent> authenticationFailureLogger() {
        return event -> LOGGER.warn("Login failed for principal={}", event.getAuthentication().getPrincipal());
    }
}
