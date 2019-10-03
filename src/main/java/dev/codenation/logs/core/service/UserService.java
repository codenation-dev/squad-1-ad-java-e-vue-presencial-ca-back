package dev.codenation.logs.core.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.valueObject.UserAuth;
import dev.codenation.logs.domain.valueObject.UserInformation;
import dev.codenation.logs.core.dto.request.UserRequestDTO;
import dev.codenation.logs.core.exception.message.user.UserNotFoundException;
import dev.codenation.logs.core.mapper.UserMapper;
import dev.codenation.logs.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends AbstractService<UserRepository, User, UUID> {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User save(UserRequestDTO dto) {
        return (User) repository.saveAndFlush(mapper.map(dto));
    }

    public List<UserInformation> findAllInList() {
        return mapper.map(repository.findAll());
    }

    public UserInformation getUserInformation() {
        return mapper.mapInf(getUserFromUserAuth());
    }

    private User getUserFromUserAuth() {
        UserAuth principal = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail
                (principal.getUsername());
    }

    public UserInformation getUserInformation(UUID id) throws UserNotFoundException {
        return mapper.mapInf(findById(id).orElseThrow(UserNotFoundException::new));
    }

    public UserInformation delete(UUID userId) throws UserNotFoundException {
        Optional<User> user = repository.findById(userId);
        return user.map(u -> {
            u.setActive(false);
            return mapper.mapInf(u);
        }).orElseThrow(UserNotFoundException::new);
    }
}
