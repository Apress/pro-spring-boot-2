package com.apress.todo.client;

import com.apress.todo.configuration.ToDoClientProperties;
import com.apress.todo.domain.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@AllArgsConstructor
@Data
public class ToDoClient {

    private RestTemplate restTemplate;
    private ToDoClientProperties props;

    public ToDo add(ToDo toDo){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .uri(URI.create(this.props.getHost())).path(this.props.getPath()).build();

        ResponseEntity<ToDo> response =
                this.restTemplate.exchange(
                        RequestEntity.post(uriComponents.toUri())
                                .body(toDo)
                        ,new ParameterizedTypeReference<ToDo>() {});

        return response.getBody();
    }

    public ToDo findById(String id){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .uri(URI.create(this.props.getHost())).pathSegment(this.props.getPath(), "/{id}")
                .buildAndExpand(id);

        ResponseEntity<ToDo> response =
                this.restTemplate.exchange(
                        RequestEntity.get(uriComponents.toUri()).accept(MediaTypes.HAL_JSON).build()
                        ,new ParameterizedTypeReference<ToDo>() {});

        return response.getBody();
    }

    public Collection<ToDo> findAll() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .uri(URI.create(this.props.getHost())).build();

        Traverson traverson = new Traverson(uriComponents.toUri(), MediaTypes.HAL_JSON, MediaType.APPLICATION_JSON_UTF8);
        Traverson.TraversalBuilder tb = traverson.follow(this.props.getPath().substring(1));
        ParameterizedTypeReference<Resources<ToDo>> typeRefDevices = new ParameterizedTypeReference<Resources<ToDo>>() {};

        Resources<ToDo> toDos = tb.toObject(typeRefDevices);

        return toDos.getContent();
    }

}
