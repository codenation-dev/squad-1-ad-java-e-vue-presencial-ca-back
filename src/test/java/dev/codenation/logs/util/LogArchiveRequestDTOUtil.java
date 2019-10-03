package dev.codenation.logs.util;


import dev.codenation.logs.core.dto.request.LogArchiveRequestDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LogArchiveRequestDTOUtil extends AbstractUtil{

    public LogArchiveRequestDTO createLogArchiveRequestDTO(UUID id_log, UUID id_user) {
        return LogArchiveRequestDTO.builder()
                .archived(Boolean.TRUE)
                .id(id_log)
                .userId(id_user)
                .build();
    }

}
