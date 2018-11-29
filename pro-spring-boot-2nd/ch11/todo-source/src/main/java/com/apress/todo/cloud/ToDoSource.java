package com.apress.todo.cloud;

import com.apress.todo.config.ToDoProperties;
import com.apress.todo.converter.ToDoConverter;
import com.apress.todo.domain.ToDo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;

@EnableConfigurationProperties(ToDoProperties.class)
@EnableBinding(Source.class)
public class ToDoSource {

    private ToDoProperties props;
    private ToDoConverter converter;

    public ToDoSource(ToDoProperties props, ToDoConverter converter){
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
                .filter(ToDo.class, ToDo::isCompleted)
                .channel(Source.OUTPUT)
                .get();
    }

}
