package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogCreationDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.util.*;
import org.json.JSONObject;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LogControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LogArchiveRequestDTOUtil logArchiveRequestDTOUtil;

    @Autowired
    private LogCreationDTOUtil logCreationDTOUtil;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private LogUtil logUtil;

    @Autowired
    private LogFilterRequestDTOUtil logFilterRequestDTOUtil;

    @Value("${security.oauth2.client.client-id}")
    private String client;
    @Value("${security.oauth2.client.client-secret}")
    private String secret;
    private String token = "";
    private JacksonJsonParser parser = new JacksonJsonParser();

    @Test
    public void WhenSaveNewLog_ThenReturnSucess() throws Exception {
        getAuthHeader();

        LogCreationDTO logDTO = logCreationDTOUtil.createLogCreationDTOUtil(UUID.randomUUID());

        mvc.perform(post("/logs")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void WhenSaveWithoutAuthentication_ThenReturnError() throws Exception {
        getAuthHeader();

        LogCreationDTO logDTO = logCreationDTOUtil.createLogCreationDTOUtil(UUID.randomUUID());

        mvc.perform(post("/logs")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO)))
                .andExpect(status().is(401));
    }

    @Test
    public void WhenFindByNonExistentId_ThenReturnError() throws Exception{
        getAuthHeader();

        UUID uuid = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.get("/logs/" + uuid.toString())
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void WhenFindByExistentId_ThenReturnSucess() throws Exception {
        getAuthHeader();

        UUID user_id = UUID.randomUUID();
        User user = userUtil.createUser();
        user.setId(user_id);

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(userUtil.convertObjectToJsonBytes(user)));

        LogCreationDTO logDTO = logCreationDTOUtil.createLogCreationDTOUtil(user_id);

        String result = mvc.perform(post("/logs")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject log = new JSONObject(result);

        mvc.perform(get("/logs/" + log.get("id"))
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.details", is("Details")))
                .andExpect(jsonPath("$.message", is("Error")))
                .andExpect(jsonPath("$.origin.origin", is("Origin")))
                .andExpect(jsonPath("$.origin.environment", is("DEV")))
                .andExpect(jsonPath("$.severity", is("WARNING")))
                .andExpect(jsonPath("$.reportedBy.id", is(log.getJSONObject("reportedBy").get("id"))));
    }

    @Test
    public void WhenFindByIdWithoutAuthentication_ThenReturnError() throws Exception {
        getAuthHeader();

        UUID uuid = UUID.randomUUID();

        mvc.perform(get("/logs/" + uuid.toString()))
                .andExpect(status().is(401));
    }

    @Test
    public void WhenDeleteByExistentId_ThenReturnSucess() throws Exception {
        getAuthHeader();

        UUID user_id = UUID.randomUUID();
        User user = userUtil.createUser();
        user.setId(user_id);

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(userUtil.convertObjectToJsonBytes(user)));

        LogCreationDTO logDTO = logCreationDTOUtil.createLogCreationDTOUtil(user_id);

        String result = mvc.perform(post("/logs")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject log = new JSONObject(result);

        mvc.perform(MockMvcRequestBuilders.delete("/logs/" + log.get("id"))
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk());
    }

    @Test
    public void WhenDeleteByNonExistentId_ThenReturnError() throws Exception{
        getAuthHeader();

        UUID uuid = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.delete("/logs/" + uuid.toString())
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void WhenDeleteWithoutAuthentication_ThenReturnError() throws Exception {
        getAuthHeader();

        UUID uuid = UUID.randomUUID();

        mvc.perform(delete("/logs/" + uuid.toString()))
                .andExpect(status().is(401));
    }

    @Test
    public void WhenArchiveWithoutAuthentication_ThenReturnError() throws Exception {
        getAuthHeader();

        UUID id_log = UUID.randomUUID();

        LogArchiveRequestDTO logDTO = logArchiveRequestDTOUtil.createLogArchiveRequestDTO(id_log, UUID.randomUUID());

        mvc.perform(patch("/logs/archive/" + id_log.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO)))
                .andExpect(status().is(401));
    }

    @Test
    public void WhenArchiveByNonExistentId_ThenReturnError() throws Exception {
        getAuthHeader();

        UUID id_log = UUID.randomUUID();

        LogArchiveRequestDTO logDTO = logArchiveRequestDTOUtil.createLogArchiveRequestDTO(id_log, UUID.randomUUID());

        mvc.perform(patch("/logs/archive/" + id_log.toString())
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO)))
                .andExpect(status().isNotFound());
    }


    @Test
    public void WhenFindAllWithoutAuthorization_ThenReturnError() throws Exception {
        getAuthHeader();

        LogFilterRequestDTO logDTO = logFilterRequestDTOUtil.createLogFilterRequestDTO();

        mvc.perform(get("/logs/sumarized")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO))
                .param("sort.unsorted", String.valueOf(Boolean.TRUE))
                .param("unpaged", String.valueOf(Boolean.TRUE)))
                .andExpect(status().is(401));
    }

    @Test
    public void WhenFindAll_ThenReturnSucess() throws Exception {
        getAuthHeader();

        UUID user_id = UUID.randomUUID();
        User user = userUtil.createUser();
        user.setId(user_id);

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(userUtil.convertObjectToJsonBytes(user)));

        LogCreationDTO logDTO1 = logCreationDTOUtil.createLogCreationDTOUtil(user_id);
        logDTO1.setSeverity("ERROR");
        String result = mvc.perform(post("/logs")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO1)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject log1 = new JSONObject(result);

        LogCreationDTO logDTO2 = logCreationDTOUtil.createLogCreationDTOUtil(user_id);
        logDTO2.setSeverity("FATAL");
        result = mvc.perform(post("/logs")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO2)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject log2 = new JSONObject(result);

        LogCreationDTO logDTO3 = logCreationDTOUtil.createLogCreationDTOUtil(user_id);
        logDTO3.setSeverity("WARNING");
        result = mvc.perform(post("/logs")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(logDTO3)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject log3 = new JSONObject(result);

        LogFilterRequestDTO filterDTO = logFilterRequestDTOUtil.createLogFilterRequestDTO();

        ResultActions logsList = mvc.perform(get("/logs/sumarized")
                .header("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(logCreationDTOUtil.convertObjectToJsonBytes(filterDTO)));
    }

    private void getAuthHeader() throws Exception {
        if (token.isEmpty()) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "password");
            params.add("username", "admin@squad.one.arrebenta.com.br");
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

            this.token = accessToken;
        }
    }

}
