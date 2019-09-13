package dev.codenation.logs.repository;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
import dev.codenation.logs.service.LogService;
import dev.codenation.logs.service.UserService;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

@MockBean
public class LogServiceTest {

    @Mock
    private LogService service;

    @Mock
    private UserService userService;

// Realizar os testes para a classe Service, pois a classe repository o java garante que estão funcionando.
//    @Test
//    @Transactional
//    public void givenLogSecurityAndLogEnvironment_whenGetAListOfLogs_thenCorrect() {
//
//        User user = getUser();
//        Log log1 = getLog(user, "teste1");
//        Log log2 = getLog(user, "teste2");
//        List<Log> logExample = Collections.singletonList(getLog());
//
//        userRepository.save(user);
//        repository.saveAll(Arrays.asList(log1, log2));
//        List<Log> results = repository.findAll(logExample);
//
//        Assert.assertThat(results, Matchers.hasSize(2));
//    }

    private Log getLog() {
        return Log.builder()
                .logDetail(getLogDetail())
                .origin(getOrigin())
                .build();
    }

    private Origin getOrigin() {
        return Origin.builder()
                .environment(Environment.DEV)
                .build();
    }

    private LogDetail getLogDetail() {
        return LogDetail.builder()
                .severity(Severity.ERROR)
                .build();
    }

    private Log getLog(User user, String teste1) {
        return Log.builder()
                .hash("hash")
                .logDetail(LogDetail.builder()
                        .message(teste1)
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

    private User getUser() {
        return User.builder()
                .firstName("thalita")
                .lastName("oliveira")
                .email("thalicarol@gmail.com")
                .password("blubouasdofas")
                .build();
    }
}
