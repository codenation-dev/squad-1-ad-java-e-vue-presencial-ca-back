package dev.codenation.logs.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public interface LogSumaryResponseDTO {
    public Integer getHash();
    public UUID getId();
    public String getMessage();
    public String getDetails();
    public String getSeverity();
    public String getEnvironment();
    public String getOrigin();
    public String getArchived();
    public UUID getReportedBy();
    public LocalDateTime getCreatedAt();
    public Long getTotal();
}
