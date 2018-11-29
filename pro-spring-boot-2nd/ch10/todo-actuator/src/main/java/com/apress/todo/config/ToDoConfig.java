package com.apress.todo.config;

import com.apress.todo.interceptor.ToDoMetricInterceptor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

@EnableConfigurationProperties(ToDoProperties.class)
@Configuration
public class ToDoConfig {


    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry registry) {
        return new MappedInterceptor(new String[]{"/**"}, new ToDoMetricInterceptor(registry));
    }


    /*
    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "todo-app");
    }
    */

    /*
    @Bean
    MeterFilter meterFilter() {
        return MeterFilter.denyUnless( f -> f.getName().contains("api"));
    }
    */
}
