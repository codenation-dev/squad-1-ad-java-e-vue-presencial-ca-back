package dev.codenation.logs.exception.handler;

import dev.codenation.logs.exception.handler.log.LogExceptionHandler;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
import dev.codenation.logs.repository.LogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@SpringBootTest
public class AbstractExceptionHandlerTest {

    //todo realizar testes para todos os handlers presentes até agora e validar com a thalita a qualidade desses.

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    LogRepository repository;

    @MockBean
    LogNotFoundMessage logNotFoundMessage;

    @InjectMocks
    LogExceptionHandler exceptionHandler;

    @Test
    public void hereWeWillGetErrorNotFound() throws Exception {

        UUID uuid = UUID.randomUUID();

        when(logNotFoundMessage.getMessage()).thenReturn("");
//        when(repository.findById(uuid)).thenReturn(Optional.empty());

//        when(controller.findById(uuid)).thenReturn(handler.logNotFoundException());

        System.out.println(mockMvc.perform(get("/logs/"+uuid))+"\n\n\n MAcaco veio\n\n\n\n\n\n\n");

        mockMvc.perform(get("/logs/" + uuid))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(logNotFoundMessage.getMessage())));

    }

    @Before
    public void setUp(){
        mockMvc = webAppContextSetup(this.context).build();
    }


}