package dev.codenation.logs.mapper;

import dev.codenation.logs.authentication.AuthorizationServerConfig;
import dev.codenation.logs.authentication.WebSecurityConfig;
import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.enums.EnvironmentEnum;
import dev.codenation.logs.domain.enums.SeverityEnum;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertThat;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogMapperTest {


    @MockBean
    private AuthorizationServerConfig config;

    @MockBean
    private WebSecurityConfig securityConfig;

    @Autowired
    private LogMapper mapper;

    private UUID id_user = UUID.randomUUID();

    private UUID id_log = UUID.randomUUID();

    @Test
    public void WhenMapLogFilterRequestDTO_ReturnLog(){

        LogFilterRequestDTO request = createLogFilterRequestDTO();

        Log log = mapper.map(request);

        assertThat(log.getId(), Matchers.equalTo(id_log));
        //todo revisar
//        assertThat(log.getHash().toString(), Matchers.equalTo("1234"));
        assertThat(log.getLogDetail().getDetails(), Matchers.equalTo("Details"));
        assertThat(log.getArchived(), Matchers.equalTo(Boolean.TRUE));
        assertThat(log.getArchivedBy().getId(), Matchers.equalTo(id_user));
        assertThat(log.getOrigin().getEnvironment(), Matchers.equalTo(EnvironmentEnum.DEV));
        assertThat(log.getLogDetail().getMessage(), Matchers.equalTo("Message"));
        assertThat(log.getOrigin().getOrigin(), Matchers.equalTo("Origin"));
        assertThat(log.getReportedBy().getId(), Matchers.equalTo(id_user));
        assertThat(log.getLogDetail().getSeverity(), Matchers.equalTo(SeverityEnum.WARNING));
    }


    private LogFilterRequestDTO createLogFilterRequestDTO() {
        return LogFilterRequestDTO.builder()
                .id(id_log)
                .hash("1234")
                .details("Details")
                .archived(Boolean.TRUE)
                .archivedBy(id_user)
                .environment(EnvironmentEnum.DEV)
                .message("Message")
                .origin("Origin")
                .reportedBy(id_user)
                .severity(SeverityEnum.WARNING)
                .build();
    }

}
