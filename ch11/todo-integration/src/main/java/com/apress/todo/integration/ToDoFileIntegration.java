package com.apress.todo.integration;

import com.apress.todo.config.ToDoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.splitter.FileSplitter;

import java.io.File;


@EnableConfigurationProperties(ToDoProperties.class)
@Configuration
public class ToDoFileIntegration {

    private ToDoProperties props;
    private ToDoConverter converter;

    public ToDoFileIntegration(ToDoProperties props, ToDoConverter converter){
        this.props = props;
        this.converter = converter;
    }

    @Bean
    public IntegrationFlow fileFlow(){
        return IntegrationFlows
                .from(Files
                        .inboundAdapter(new File(this.props.getDirectory()))
                        .preventDuplicates(true)
                        .patternFilter(this.props.getFilePattern())
                        , e -> e.poller(Pollers.fixedDelay(5000L))
                )
                .split(Files.splitter().markers())
                .filter(p -> !(p instanceof FileSplitter.FileMarker))
                .transform(Transformers.converter(converter))
                //.filter(ToDo.class, ToDo::isCompleted)
                .handle("toDoMessageHandler","process")
                .get();

    }
}
