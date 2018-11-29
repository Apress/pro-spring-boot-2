package com.apress.todo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToDoSimpleRestTemplateTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void toDoTest() {
        String body = this.restTemplate.getForObject("/todo", String.class);
        assertThat(body).contains("Read a Book");
    }

}
