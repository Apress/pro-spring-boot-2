package com.apress.todo.controller;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ToDoController {

    private ToDoRepository repository;

    public ToDoController(ToDoRepository repository){
        this.repository = repository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDo>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> findById(@PathVariable String id){
        ToDo result =  this.repository.findById(id);
        if(result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDo> save(@RequestBody ToDo toDo){
        this.repository.save(toDo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toDo.getId())
                .toUri();
        return ResponseEntity.created(location).body(toDo);
    }
}
