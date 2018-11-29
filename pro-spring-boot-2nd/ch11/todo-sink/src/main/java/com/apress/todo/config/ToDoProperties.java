package com.apress.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="todo.mail")
public class ToDoProperties {

    private String from;
    private String to;
    private String subject;
    private String host;
    private String username;
    private String password;
    private Integer port;

}
