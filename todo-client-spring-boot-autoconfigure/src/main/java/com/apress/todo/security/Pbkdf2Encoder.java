package com.apress.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class Pbkdf2Encoder {

    @Bean
    public ToDoSecurity utils(){
        return new ToDoSecurity(new Pbkdf2PasswordEncoder());
    }
}
