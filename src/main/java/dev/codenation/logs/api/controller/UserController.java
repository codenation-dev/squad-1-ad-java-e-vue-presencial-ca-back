package dev.codenation.logs.api.controller;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.valueObject.UserInformation;
import dev.codenation.logs.core.dto.request.UserRequestDTO;
import dev.codenation.logs.core.exception.message.log.LogNotFoundException;
import dev.codenation.logs.core.exception.message.user.UserNotFoundException;
import dev.codenation.logs.core.mapper.UserMapper;
import dev.codenation.logs.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    public User createUser(@Valid @RequestBody UserRequestDTO user) {
        return service.save(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserInformation> findAll() {
        return service.findAllInList();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInformation findUser(@PathVariable UUID id) throws UserNotFoundException, LogNotFoundException {
        return service.getUserInformation(id);
    }

    @GetMapping("/me")
    public UserInformation me() {
        return service.getUserInformation();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User patch(@PathVariable UUID id, @RequestBody UserRequestDTO user) throws Exception {
        if (id.equals(user.getId())) {
            throw new Exception(); //ToDo criar exceção
        }
        return service.save(mapper.map(user));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInformation delete(@PathVariable UUID id) throws UserNotFoundException {
        return service.delete(id);
    }
}
