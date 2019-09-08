package dev.codenation.logs.domain.valueObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.codenation.logs.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ArchiveDetail {

    @NotNull
    private Boolean archived;

    @ManyToOne
    private User archivedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime archivedAt;
}
