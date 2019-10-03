package dev.codenation.logs.util;

import dev.codenation.logs.domain.entity.User;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Random;

@Component
public class UserUtil extends AbstractUtil{

    public User createUser(){

        String emailRandom = random(10) + "@teste.com";
        return User.builder()
                .email(emailRandom)
                .firstName("User")
                .lastName("Test")
                .password("Password")
                .build();
    }
    private String random(int length){
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}
