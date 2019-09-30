package dev.codenation.logs.dto.response;

import java.time.LocalDateTime;


public interface LogSumaryResponseDTO {

    public Integer getHash();

    public String getId();

    public String getMessage();

    public String getDetails();

    public String getSeverity();

    public String getEnvironment();

    public String getOrigin();

    public String getArchived();

    public String getReportedBy();

    public LocalDateTime getCreatedAt();

    public Long getTotal();

}
