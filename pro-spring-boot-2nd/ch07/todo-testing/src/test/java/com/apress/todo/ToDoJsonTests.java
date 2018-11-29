package com.apress.todo;


import com.apress.todo.domain.ToDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class ToDoJsonTests {

    @Autowired
    private JacksonTester<ToDo> json;

    @Test
    public void toDoSerializeTest() throws Exception {
        ToDo toDo = new ToDo("Read a Book");

        // Assert against a `.json` file in the same package as the test: resource/com/apress/todo/todo.json
        assertThat(this.json.write(toDo)).isEqualToJson("todo.json");

        assertThat(this.json.write(toDo)).hasJsonPathStringValue("@.description");
        assertThat(this.json.write(toDo)).extractingJsonPathStringValue("@.description")
                .isEqualTo("Read a Book");
    }

    @Test
    public void toDoDeserializeTest() throws Exception {
        String content = "{\"description\":\"Read a Book\",\"completed\": true }";
        assertThat(this.json.parse(content))
                .isEqualTo(new ToDo("Read a Book", true));
        assertThat(this.json.parseObject(content).getDescription()).isEqualTo("Read a Book");
    }

}
