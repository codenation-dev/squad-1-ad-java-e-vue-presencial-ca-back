package dev.codenation.logs.dto.request;

import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LogFilterRequestDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private UUID id;

    private String hash;

    private String message;

    private String details;

    private Severity severity;

    private Environment environment;

    private String origin;

    private Boolean archived;

    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private UUID archivedBy;

    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private UUID reportedBy;

}
