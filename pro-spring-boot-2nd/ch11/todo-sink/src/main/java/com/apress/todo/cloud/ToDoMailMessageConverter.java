package com.apress.todo.cloud;

import com.apress.todo.config.ToDoProperties;
import com.apress.todo.domain.ToDo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;


@Component
public class ToDoMailMessageConverter implements Converter<byte[], SimpleMailMessage> {

    private ToDoProperties props;

    public ToDoMailMessageConverter(ToDoProperties props){
        this.props = props;
    }

    @Override
    public SimpleMailMessage convert(byte[] bytes) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(this.props.getFrom());
        smm.setTo(this.props.getTo());
        smm.setSubject(this.props.getSubject());
        smm.setText(new String(bytes));
        return smm;
    }
}
