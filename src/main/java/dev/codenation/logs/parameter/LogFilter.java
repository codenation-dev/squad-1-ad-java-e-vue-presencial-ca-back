package dev.codenation.logs.parameter;

import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import lombok.Data;

import java.util.Optional;

import java.util.UUID;

@Data
public class LogFilter {

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


//    public UUID getId() {
//        return id;
//    }
//
//    public Optional<String> getHash() {
//        return Optional.of(hash);
//    }
//
//    public Optional<String> getMessage() {
//        return Optional.of(message);
//    }
//
//    public Optional<String> getDetails() {
//        return Optional.of(details);
//    }
//    public Optional<Severity> getSeverity() {
//        return Optional.of(severity);
//    }
//
//    public Optional<Environment> getEnvironment() {
//        return Optional.of(environment);
//    }
//
//    public Optional<String> getOrigin() {
//        return Optional.of(origin);
//    }
//
//    public Optional<Boolean> getArchived() {
//        return Optional.of(archived);
//    }
//
//    public UUID getArchivedBy() {
//        return archivedBy;
//    }
//
//    public UUID getReportedBy() {
//        return reportedBy;
//    }

}
