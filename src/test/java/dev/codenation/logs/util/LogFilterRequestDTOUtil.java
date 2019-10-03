package dev.codenation.logs.util;

import dev.codenation.logs.core.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.domain.enums.Environment;
import org.springframework.stereotype.Component;

@Component
public class LogFilterRequestDTOUtil extends AbstractUtil{

    public LogFilterRequestDTO createLogFilterRequestDTO() {
        return LogFilterRequestDTO.builder()
                .details("Details")
                .environment(Environment.DEV)
                .message("Message")
                .origin("Origin")
                .build();
    }

}
