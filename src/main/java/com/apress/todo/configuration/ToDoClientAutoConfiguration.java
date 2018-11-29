package com.apress.todo.configuration;


import com.apress.todo.client.ToDoClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Resource;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
@ConditionalOnClass({Resource.class, RestTemplateBuilder.class})
@EnableConfigurationProperties(ToDoClientProperties.class)
public class ToDoClientAutoConfiguration {


    private final Logger log = LoggerFactory.getLogger(ToDoClientAutoConfiguration.class);
    private final ToDoClientProperties toDoClientProperties;

    @Bean
    public ToDoClient client(){
        log.info(">>> Creating a ToDo Client...");
        return new ToDoClient(new RestTemplate(),this.toDoClientProperties);
    }


}
