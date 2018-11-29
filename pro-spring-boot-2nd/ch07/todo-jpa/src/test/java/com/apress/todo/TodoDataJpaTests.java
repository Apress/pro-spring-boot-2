package com.apress.todo;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoDataJpaTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ToDoRepository repository;

    @Test
    public void toDoDataTest() throws Exception {
        this.entityManager.persist(new ToDo("Read a Book"));
        Iterable<ToDo> toDos = this.repository.findByDescriptionContains("Read a Book");
        assertThat(toDos.iterator().next()).toString().contains("Read a Book");
    }

}
