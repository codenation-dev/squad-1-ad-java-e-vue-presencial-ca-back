package dev.codenation.logs.core.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginSocialRequestDTO {
    private String code;
    private String redirectUri;
}
