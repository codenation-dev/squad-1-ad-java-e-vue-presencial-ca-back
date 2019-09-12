package dev.codenation.logs.parameter;

import lombok.Data;

import java.util.UUID;

@Data
public class LogArchiveParameter {

    private UUID id;

    private UUID userId;

    private Boolean archived;

}
