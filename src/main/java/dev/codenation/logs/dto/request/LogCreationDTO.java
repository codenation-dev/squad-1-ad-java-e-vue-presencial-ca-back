package dev.codenation.logs.dto.request;

import dev.codenation.logs.domain.vo.UserInformation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class LogCreationDTO {

    private Integer hash;

    @NotNull
    private String message;

    @NotNull
    private String details;

    @NotNull
    private String severity;

    @NotNull
    private String origin;

    @NotNull
    private String environment;

    @Builder.Default
    private Boolean archived = false;

    @NotNull
    private UserInformation reportedBy;

}
