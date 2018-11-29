package com.apress.todo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ToDoConfig {

    /*
    @Bean
    public ApplicationRunner runner(MessageChannel input){
        return args -> {
            input.send(MessageBuilder.withPayload(new ToDo("buy milk today",true)).build());
        };
    }
    */
}
