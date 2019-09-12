package dev.codenation.logs.filter;

import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;

import java.util.UUID;

public class LogRequestFilter {

    private UUID id;

    private String hash;

    private String message;

    private String details;

    Severity severity;

    Environment environment;

    private String origin;

    private Boolean archived;

    private UUID archivedBy;

    private UUID reportedBy;

}
