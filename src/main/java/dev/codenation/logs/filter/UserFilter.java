package dev.codenation.logs.filter;

import lombok.Data;

import java.util.UUID;

@Data
public class UserFilter {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
