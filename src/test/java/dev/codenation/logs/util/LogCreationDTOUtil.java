package dev.codenation.logs.util;

import dev.codenation.logs.domain.vo.UserInformation;
import dev.codenation.logs.dto.request.LogCreationDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LogCreationDTOUtil extends AbstractUtil{

    public LogCreationDTO createLogCreationDTOUtil(UUID id_user){

        UserInformation user = UserInformation.builder()
                .email("test@test.com")
                .firstName("User")
                .lastName("Test")
                .id(id_user)
                .build();

        return LogCreationDTO.builder()
                .archived(Boolean.FALSE)
                .details("Details")
                .environment("DEV")
                .message("Error")
                .origin("Origin")
                .severity("WARNING")
                .reportedBy(user)
                .build();
    }

}
