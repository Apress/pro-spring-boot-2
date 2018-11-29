package com.apress.reactor.example;

import com.apress.reactor.example.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Configuration
public class FluxExample {


    static private Logger LOG = LoggerFactory.getLogger(FluxExample.class);

    @Bean
    public CommandLineRunner runFluxExample(){
        return args -> {

            EmitterProcessor<ToDo> stream = EmitterProcessor.create();

            // Log values passing through the Flux and capture the first coming signal
            Mono<List<ToDo>> promise = stream
                    .filter( s -> s.isCompleted())
                    .doOnNext(s -> LOG.info("FLUX >>> ToDo: {}", s.getDescription()))
                    .collectList()
                    .subscribeOn(Schedulers.single());

            // Publish a value
            stream.onNext(new ToDo("Read a Book",true));
            stream.onNext(new ToDo("Listen Classical Music",true));
            stream.onNext(new ToDo("Workout in the Mornings"));
            stream.onNext(new ToDo("Organize my room", true));
            stream.onNext(new ToDo("Go to the Car Wash", true));
            stream.onNext(new ToDo("SP1 2018 is coming", true));

            stream.onComplete();

            promise.block();

        };
    }
}
