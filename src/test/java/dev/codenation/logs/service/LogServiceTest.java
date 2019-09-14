package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
import dev.codenation.logs.repository.LogRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @MockBean
    private LogRepository logRepository;

    @Test
    public void whenValidId_LogShouldBeFoundById(){
        UUID id = UUID.randomUUID();
        Optional<Log> log = Optional.ofNullable(createNewLog(id));
        Mockito.when(logRepository.findById(id))
                .thenReturn(log);

        Optional<Log> logFound = logService.findById(log.get().getId());

        Assert.assertThat(logFound.get().getId(), CoreMatchers.equalTo(log.get().getId()));
    }

    @Test
    public void whenSave_LogShouldBeReturned(){
        UUID id = UUID.randomUUID();
        Log log = createNewLog(id);
        Mockito.when(logRepository.save(log))
                .thenReturn(log);

        Log logSaved = logRepository.save(log);

        Assert.assertThat(logSaved, CoreMatchers.equalTo(log));
    }

    @Test
    public void WhenFindAll_AllLogsShouldBeReturned(){
        UUID id = UUID.randomUUID();
        Log log1 = createNewLog(id);
        id = UUID.randomUUID();
        Log log2 = createNewLog(id);

        List<Log> logs = Arrays.asList(log1, log2);

        Mockito.when(logRepository.findAll())
                .thenReturn(logs);

        List<Log> listLogsFound = logRepository.findAll();
        Assert.assertThat(listLogsFound, CoreMatchers.equalTo(logs));
    }

    private Log createNewLog(UUID id){
        User user = createNewUser();
        return Log.builder()
                .id(id)
                .hash("hash")
                .logDetail(LogDetail.builder()
                        .message("teste1")
                        .severity(Severity.ERROR)
                        .details("stack trace de teste")
                        .build())
                .origin(Origin.builder()
                        .environment(Environment.DEV)
                        .origin("localhost")
                        .build())
                .reportedBy(user)
                .build();
    }

    private User createNewUser(){
        return User.builder()
                .firstName("user")
                .lastName("teste")
                .email("user@teste.com")
                .password("senha")
                .build();
    }

}
