package com.apress.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptEncoder {

    @Bean
    public ToDoSecurity utils(){
        return new ToDoSecurity(new BCryptPasswordEncoder(16));
    }
}
