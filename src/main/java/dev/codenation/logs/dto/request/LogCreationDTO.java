package dev.codenation.logs.dto.request;

import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.vo.UserInformation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    private Boolean archived = false;

    @NotNull
    private UserInformation reportedBy;
}
