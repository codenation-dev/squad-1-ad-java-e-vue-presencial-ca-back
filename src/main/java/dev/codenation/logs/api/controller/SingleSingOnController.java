package dev.codenation.logs.api.controller;

import dev.codenation.logs.core.dto.request.LoginSocialRequestDTO;
import dev.codenation.logs.core.service.oauth.FacebookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/sso")
public class SingleSingOnController {

    @Autowired
    private FacebookService service;

    @GetMapping("facebook/url")
    public String createFacebookAuthorization() {
        return service.createFacebookAuthUrl();
    }

    @PostMapping("facebook/login")
    public OAuth2AccessToken createFacebookAccessToken(@RequestBody LoginSocialRequestDTO loginDTO) {
        return Optional.ofNullable(service.login(loginDTO)).orElse(null);
    }
}
