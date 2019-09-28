package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import dev.codenation.logs.dto.request.UserRequestDTO;
import dev.codenation.logs.mapper.UserMapper;
import dev.codenation.logs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserRequestDTO user) {
        return service.save(mapper.map(user));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable UUID id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(UserFilterRequestDTO filter) {
        Example<User> userExample = Example.of(mapper.map(filter));
        return service.findAll(userExample);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User patch(@PathVariable UUID id, UserRequestDTO user) {
        return service.save( mapper.map(user));
    }
}
