package com.apress.todo.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="todo.client")
public class ToDoClientProperties {

    private String host = "http://localhost:8080";
    private String path = "/toDos";

}
