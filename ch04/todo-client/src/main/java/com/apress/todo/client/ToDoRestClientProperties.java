package com.apress.todo.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="todo")
@Data
public class ToDoRestClientProperties {

    private String url;
    private String basePath;

}
