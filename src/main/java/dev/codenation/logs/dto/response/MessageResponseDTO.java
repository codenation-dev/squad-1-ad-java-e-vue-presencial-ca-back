package dev.codenation.logs.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageResponseDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
