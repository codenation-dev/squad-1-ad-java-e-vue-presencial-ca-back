package dev.codenation.logs.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogRepositoryTest {

    @Test
    public void teste() {

    }

//    @Autowired
//    private LogRepository repository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    @Transactional
//    public void givenLogSecurityAndLogEnvironment_whenGetAListOfLogs_thenCorrect() {
//
//        User user = createNewUser();
//        userRepository.save(user);
//
//        Log log1 = Log.builder()
//                .hash("hash")
//                .logDetail(LogDetail.builder()
//                        .message("teste1")
//                        .severity(Severity.ERROR)
//                        .details("stack trace de teste")
//                        .build())
//                .origin(Origin.builder()
//                        .environment(Environment.DEV)
//                        .origin("localhost")
//                        .build())
//                .reportedBy(user)
//                .build();
//        Log log2 = Log.builder()
//                .hash("hash")
//                .logDetail(LogDetail.builder()
//                        .message("teste2")
//                        .severity(Severity.ERROR)
//                        .details("stack trace de teste")
//                        .build())
//                .origin(Origin.builder()
//                        .environment(Environment.DEV)
//                        .origin("localhost")
//                        .build())
//                .reportedBy(user)
//                .build();
//
//        repository.saveAll(Arrays.asList(log1, log2));
//
//        Example<Log> logExample = Example.of(Log.builder()
//                .logDetail(LogDetail.builder()
//                        .severity(Severity.ERROR)
//                        .build())
//                .origin(Origin.builder()
//                        .environment(Environment.DEV)
//                        .build())
//                .build());
//
//        List<Log> results = repository.findAll(logExample);
//        Assert.assertThat(results, Matchers.hasSize(2));
//    }
//
//    private User createNewUser() {
//        return User.builder()
//                .firstName("thalita")
//                .lastName("oliveira")
//                .email("thalicarol@gmail.com")
//                .password("blubouasdofas")
//                .build();
//    }
}
