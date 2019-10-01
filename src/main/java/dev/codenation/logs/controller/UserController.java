package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.vo.UserInformation;
import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import dev.codenation.logs.dto.request.UserRequestDTO;
import dev.codenation.logs.exception.message.user.UserNotFoundException;
import dev.codenation.logs.mapper.UserMapper;
import dev.codenation.logs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    private UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody User user) {
        service.save(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> findAll(UserFilterRequestDTO filter) {
        return service.findAll(filter);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUser(@PathVariable UUID id) throws UserNotFoundException {
        return service.findById(id).orElseThrow(UserNotFoundException::new);
    }


    @GetMapping(value = "/username")
    public UserInformation getUserInfo(){
        return service.getUserInformation();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User patch(@PathVariable UUID id, @RequestBody UserRequestDTO user) throws Exception {
        if(id.equals(user.getId())){
            throw new Exception(); //ToDo criar exceção
        }
        return service.save(mapper.map(user));
    }
}
