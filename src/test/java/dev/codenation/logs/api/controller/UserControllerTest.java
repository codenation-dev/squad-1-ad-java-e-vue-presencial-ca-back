package dev.codenation.logs.api.controller;

import dev.codenation.logs.core.dto.request.UserRequestDTO;
import dev.codenation.logs.util.UserRequestDTOUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRequestDTOUtil userRequestDTOUtil;


    @Value("${security.oauth2.client.client-id}")
    private String client;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    private String token = "";

    private JacksonJsonParser parser = new JacksonJsonParser();

    @Before
    public void getAuthHeader() throws Exception {
        if (token.isEmpty()) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "password");
            params.add("username", "admin@logsone.com");
            params.add("password", "SenhaMestra");

            ResultActions login = mvc.perform(
                    post("/oauth/token")
                            .params(params)
                            .accept("application/json;charset=UTF-8")
                            .with(httpBasic(client, secret)))
                    .andExpect(status().isOk());

            String accessToken = parser.parseMap(login
                    .andReturn()
                    .getResponse()
                    .getContentAsString()).get("access_token").toString();

            token = accessToken;
        }
    }

    @Test
    public void verifyingIfSavesUserSuccessfully() throws Exception {
        UserRequestDTO userRequestDTO = userRequestDTOUtil.createUserRequestDTO();

        ResultActions user = mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTO)));

        user.andExpect(status().isCreated());
    }

    @Test
    public void verifyIfReturnsUserInfo() throws Exception {
        ResultActions user = mvc.perform(get("/user/me")
                .header("Authorization", "Bearer " + this.token));

        user.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Admin")))
                .andExpect(jsonPath("$.lastName", is("2.0")))
                .andExpect(jsonPath("$.email", is("admin@logsone.com")));
    }


//    @Test
//    public void verifyIfFindAllWhenAuthenticated_thenReturn200() throws Exception {
//
//        mvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOJoao())));
//
//        mvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOMaria())));
//
//        ResultActions userList = mvc.perform(get("/user")
//                .header("Authorization", "Bearer " + this.token));
//
//        userList.andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].firstName", is("Admin") ))
//                .andExpect(jsonPath("$.[1].firstName", is("joao")))
//                .andExpect(jsonPath("$.[2].firstName", is("maria")))
//                .andExpect(jsonPath("$.[3].firstName", is("maria")));
//
//    }
}
