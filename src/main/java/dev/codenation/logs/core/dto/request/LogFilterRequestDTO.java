package dev.codenation.logs.core.dto.request;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogFilterRequestDTO {

    private UUID id;

    private String hash;

    private String message;

    private String details;

    private Severity severity;

    private Environment environment;

    private String origin;

    private Boolean archived;

    private UUID archivedBy;

    private UUID reportedBy;

}
