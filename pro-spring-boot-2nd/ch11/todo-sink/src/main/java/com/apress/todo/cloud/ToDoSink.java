package com.apress.todo.cloud;

import com.apress.todo.config.ToDoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.mail.javamail.MimeMessageHelper;

@EnableConfigurationProperties(ToDoProperties.class)
@EnableBinding(Sink.class)
public class ToDoSink {

    private Logger log = LoggerFactory.getLogger(ToDoSink.class);
    private ToDoProperties props;
    private ToDoMailMessageConverter converter;

    public ToDoSink(ToDoProperties props, ToDoMailMessageConverter converter){
        this.props = props;
        this.converter = converter;
    }

    @Bean
    public IntegrationFlow sendMailFlow() {
        return IntegrationFlows.from(Sink.INPUT)
                .transform(Transformers.converter(converter))
                .handle(Mail.outboundAdapter(this.props.getHost())
                                .port(this.props.getPort())
                                .credentials(this.props.getUsername(), this.props.getPassword())
                                .protocol("smtp")
                                .javaMailProperties(p -> {
                                    p.put("mail.debug", "true");
                                    p.put("mail.smtp.starttls.enable", "true");
                                    p.put("mail.smtp.auth", "true");
                                    }),
                        e -> e.id("sendMailEndpoint"))
                .get();
    }
}
