package dev.codenation.logs.util;


import dev.codenation.logs.core.dto.request.LogCreationDTO;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.UserInformation;
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
                .environment(Environment.DEV)
                .message("Error")
                .origin("Origin")
                .severity(Severity.WARNING)
                .reportedBy(user)
                .build();
    }

}
