package com.apress.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "todo.ws")
public class ToDoProperties {

    private String app = "/todo-api-ws";
    private String broker = "/todo";
    private String endpoint = "/stomp";

}
