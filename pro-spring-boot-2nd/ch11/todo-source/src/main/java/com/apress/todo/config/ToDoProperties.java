package com.apress.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="todo")
public class ToDoProperties {

    private String directory;
    private String filePattern;

}
