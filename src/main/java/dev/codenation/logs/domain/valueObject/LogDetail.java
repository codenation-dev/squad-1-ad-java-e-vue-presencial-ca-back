package dev.codenation.logs.domain.valueObject;

import dev.codenation.logs.domain.enums.ErrorLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LogDetail {

    @NotNull
    private String message;

    @NotNull
    private String details;

    @NotNull
    ErrorLevel errorLevel;

}
