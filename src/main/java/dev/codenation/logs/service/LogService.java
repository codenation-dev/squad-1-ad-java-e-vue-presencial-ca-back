package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LogService extends AbstractService<LogRepository, Log, UUID>{

    @Autowired
    public LogService(LogRepository repository) {
        super(repository);
    }

}
