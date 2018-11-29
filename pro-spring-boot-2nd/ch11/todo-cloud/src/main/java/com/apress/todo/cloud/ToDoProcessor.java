package com.apress.todo.cloud;

import com.apress.todo.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.time.LocalDateTime;

//@EnableBinding(Processor.class)
public class ToDoProcessor {

    private Logger log = LoggerFactory.getLogger(ToDoProcessor.class);

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public ToDo transformToUpperCase(ToDo message) {
        log.info("Processing >>> {}", message);
        ToDo result = message;
        result.setDescription(message.getDescription().toUpperCase());
        result.setCompleted(true);
        result.setModified(LocalDateTime.now());
        log.info("Message Processed >>> {}", result);
        return result;
    }
}
