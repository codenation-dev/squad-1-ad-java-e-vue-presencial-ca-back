package dev.codenation.logs.parameter;

import lombok.Data;

import java.util.UUID;

@Data
public class UserFindFilter {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
