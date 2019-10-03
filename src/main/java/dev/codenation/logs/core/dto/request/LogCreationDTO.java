package dev.codenation.logs.core.dto.request;

import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.UserInformation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class LogCreationDTO {

    @NotNull
    private String message;

    @NotNull
    private String details;

    @NotNull
    private Severity severity;

    @NotNull
    private String origin;

    @NotNull
    private Environment environment;

    @Builder.Default
    private Boolean archived = false;

    @NotNull
    private UserInformation reportedBy;
}
