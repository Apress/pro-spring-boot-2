package com.apress.todo.sender;

import com.apress.todo.domain.ToDo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

//@Configuration
public class ToDoSender {

    @Bean
    public ApplicationRunner send(MessageChannel input){
        return args -> {
            input
                    .send(MessageBuilder
                    .withPayload(new ToDo("Read a Book"))
                            .build());

        };
    }
}
