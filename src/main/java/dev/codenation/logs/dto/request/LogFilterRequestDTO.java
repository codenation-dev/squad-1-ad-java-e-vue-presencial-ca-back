package dev.codenation.logs.dto.request;

<<<<<<< HEAD
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import lombok.Builder;
=======
import dev.codenation.logs.domain.enums.EnvironmentEnum;
import dev.codenation.logs.domain.enums.SeverityEnum;
>>>>>>> origin/qa
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class LogFilterRequestDTO {

    private UUID id;

    private String hash;

    private String message;

    private String details;

    private SeverityEnum severity;

    private EnvironmentEnum environment;

    private String origin;

    private Boolean archived;

    private UUID archivedBy;

    private UUID reportedBy;

}
