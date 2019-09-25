package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends AbstractService<UserRepository, User, UUID> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public User save(User user) {
        if (!user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return (User) repository.save(user);
    }

}
