package dev.codenation.logs.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UserFilterDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
