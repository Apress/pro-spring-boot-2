package com.apress.todo;


import com.apress.todo.domain.ToDo;
import com.apress.todo.service.ToDoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(ToDoService.class)
public class ToDoRestClientTests {

    @Autowired
    private ToDoService service;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void toDoRestClientTest()
            throws Exception {
        String content = "{\"description\":\"Read a Book\",\"completed\": true }";
        this.server.expect(requestTo("/todo/my-id"))
                .andRespond(withSuccess(content,MediaType.APPLICATION_JSON_UTF8));
        ToDo result = this.service.findById("my-id");
        assertThat(result).isNotNull();
        assertThat(result.getDescription()).contains("Read a Book");
    }

}
