package com.apress.todo.repository;

import com.apress.todo.domain.ToDo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ToDoRepository {

    private List<ToDo> toDos = new LinkedList<ToDo>(Arrays.asList(
            new ToDo("Read a Book"),
            new ToDo("Buy Milk"),
            new ToDo("Go to SpringOne Platform 2018", true)
    ));


    public Iterable<ToDo> findAll(){
        return this.toDos;
    }

    public ToDo findById(String id){
        return toDos.stream().filter( t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public ToDo save(ToDo t){
        this.toDos.add(t);
        return t;
    }
}
