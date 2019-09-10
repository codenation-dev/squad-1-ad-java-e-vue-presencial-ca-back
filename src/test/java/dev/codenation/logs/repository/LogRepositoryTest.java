package dev.codenation.logs.repository;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogRepositoryTest {

    //@MockBean
    @Autowired
    private LogRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Test
   @Transactional
    public void givenLogSecurityAndLogEnvironment_whenGetAListOfLogs_thenCorrect() {

        User u = User.builder()
                .firstName("thalita")
                .lastName("oliveira")
                .email("thalicarol@gmail.com")
                .password("blubouasdofas")
                .build();

        User save = userRepository.save(u);

        Log log1 = Log.builder()
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
                .reportedBy(save)
                .build();
        Log log2 = Log.builder()
                .hash("hash")
                .logDetail(LogDetail.builder()
                        .message("teste2")
                        .severity(Severity.ERROR)
                        .details("stack trace de teste")
                        .build())
                .origin(Origin.builder()
                        .environment(Environment.DEV)
                        .origin("localhost")
                        .build())
                .reportedBy(save)
                .build();

         repository.saveAll(Arrays.asList(log1, log2));

        Example<Log> logExample = Example.of(Log.builder()
                .logDetail(LogDetail.builder()
                        .severity(Severity.ERROR)
                        .build())
                .origin(Origin.builder()
                        .environment(Environment.DEV)
                        .build())
                .build());

        List<Log> results = repository.findAll(logExample);
        Assert.assertThat(results, Matchers.hasSize(2));
    }
}
