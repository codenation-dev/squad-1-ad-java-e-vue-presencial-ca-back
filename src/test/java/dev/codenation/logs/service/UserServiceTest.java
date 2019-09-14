package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void whenValidId_UserShouldBeFoundById(){
        UUID id = UUID.randomUUID();
        Optional<User> user = Optional.ofNullable(createNewUser(id));
        Mockito.when(userRepository.findById(id))
                .thenReturn(user);

        Optional<User> userFound = userService.findById(user.get().getId());

        Assert.assertThat(userFound.get().getId(), CoreMatchers.equalTo(user.get().getId()));

    }

    @Test
    public void whenSave_UserShouldBeReturned(){
        UUID id = UUID.randomUUID();
        User user = createNewUser(id);
        Mockito.when(userRepository.save(user))
                .thenReturn(user);

        User userSaved = userRepository.save(user);

        Assert.assertThat(userSaved, CoreMatchers.equalTo(user));
    }

    private User createNewUser(UUID id){
        return User.builder()
                .id(id)
                .firstName("user")
                .lastName("teste")
                .email("user@teste.com")
                .password("senha")
                .build();
    }

}
