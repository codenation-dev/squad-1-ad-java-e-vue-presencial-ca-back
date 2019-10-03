package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.dto.request.LogCreationDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.dto.response.AllLogSummaryResponseDTO;
import dev.codenation.logs.util.LogCreationDTOUtil;
import dev.codenation.logs.util.LogFilterRequestDTOUtil;
import dev.codenation.logs.util.LogUtil;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogMapperTest {

    @Autowired
    private LogMapper mapper;

    @Autowired
    private LogFilterRequestDTOUtil logFilterRequestDTOUtil;

    @Autowired
    private LogCreationDTOUtil logCreationDTOUtil;

    @Autowired
    private LogUtil logUtil;

    @Test
    public void WhenMapLogFilterRequestDTO_ReturnLog(){

        LogFilterRequestDTO request = logFilterRequestDTOUtil.createLogFilterRequestDTO();

        Log log = mapper.map(request);

        assertThat(log.getLogDetail().getDetails(), Matchers.equalTo("Details"));
        assertThat(log.getOrigin().getEnvironment(), Matchers.equalTo(Environment.DEV));
        assertThat(log.getLogDetail().getMessage(), Matchers.equalTo("Message"));
        assertThat(log.getOrigin().getOrigin(), Matchers.equalTo("Origin"));
    }

    @Test
    public void WhenMapLogCreationDTO_ReturnLog(){

        UUID id_user = UUID.randomUUID();
        LogCreationDTO request = logCreationDTOUtil.createLogCreationDTOUtil(id_user);

        Log log = mapper.map(request);

        assertThat(log.getLogDetail().getDetails(), Matchers.equalTo("Details"));
        assertThat(log.getOrigin().getEnvironment(), Matchers.equalTo(Environment.DEV));
        assertThat(log.getLogDetail().getMessage(), Matchers.equalTo("Error"));
        assertThat(log.getOrigin().getOrigin(), Matchers.equalTo("Origin"));
        assertThat(log.getLogDetail().getSeverity(), Matchers.equalTo(Severity.WARNING));
        assertThat(log.getReportedBy().getId(), Matchers.equalTo(id_user));
    }

    @Test
    public void WhenMapLog_ReturnAllLogSummaryResponseDTO(){

        Log log = logUtil.createLog();

        AllLogSummaryResponseDTO response = mapper.map(log);

        assertThat(response.getId(), Matchers.equalTo(log.getId()));
        assertThat(response.getMessage(), Matchers.equalTo("Message"));
        assertThat(response.getDetails(), Matchers.equalTo("Details"));
        assertThat(response.getSeverity(), Matchers.equalTo("DEBUG"));
        assertThat(response.getOrigin().getOrigin(), Matchers.equalTo("localhost"));
        assertThat(response.getOrigin().getEnvironment(), Matchers.equalTo(log.getOrigin().getEnvironment()));
        assertThat(response.getReportedBy().getId(), Matchers.equalTo(log.getReportedBy().getId()));
    }

}
