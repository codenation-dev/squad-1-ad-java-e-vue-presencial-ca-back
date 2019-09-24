package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class LogService extends AbstractService<LogRepository, Log, UUID> {

    LogRepository repository;

    @Autowired
    public LogService(LogRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Object> findAllGroupByHash(Log log, Pageable pageable, Sort sort) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Example<Log> logExample = Example.of(log);

        repository.findAll(logExample, pageable);

        repository.findAllSumarized(log.getHash(),
                                    log.getLogDetail().getMessage(),
                                    log.getLogDetail().getDetails(),
                                    log.getLogDetail().getSeverity(),
                                    log.getOrigin().getEnvironment(),
                                    log.getOrigin().getOrigin(),
                                    log.getReportedBy().getId(),
                                    pageable);

       return null;
    }
}
