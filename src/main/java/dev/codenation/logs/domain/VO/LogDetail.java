package dev.codenation.logs.domain.VO;

import dev.codenation.logs.domain.enums.SeverityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LogDetail implements Serializable {

    @NotNull
    private String message;

    @NotNull
    private String details;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    SeverityEnum severity;

}
