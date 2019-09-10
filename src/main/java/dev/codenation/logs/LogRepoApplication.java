package dev.codenation.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LogRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogRepoApplication.class, args);
    }

}
