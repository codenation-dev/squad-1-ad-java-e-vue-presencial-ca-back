package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.repository.LogRepository;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LogService extends AbstractService<LogRepository, Log, UUID> {

    private LogRepository repository;

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private LogMapper mapper;


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

    public Page<Log> findAll(LogFilterRequestDTO filter, Pageable pageable) {
        Example<Log> logExample = Example.of(mapper.map(filter), ExampleMatcher.matchingAll().withIgnoreCase());
        return repository.findAll(logExample,pageable);
    }

    public Log archiveLogByIdOrElseThrowError(UUID logId, LogArchiveRequestDTO filter) throws LogNotFoundMessage {
        Log log = repository.findById(logId).orElseThrow(LogNotFoundMessage::new);
        setArchivedLogAndSave(filter, log);
        return log;
    }

    private void setArchivedLogAndSave(LogArchiveRequestDTO filter, Log log) {
        log.setArchived(filter.getArchived());
        log.setId(filter.getUserId());
        log.setArchivedBy(repositoryUser.getOne(filter.getUserId()));
        log.setArchivedAt(LocalDateTime.now());
        repository.saveAndFlush(log);
    }

    public void deleteOrElseThrowError(UUID logId) throws LogNotFoundMessage {
        Log log = repository.findById(logId).orElseThrow(LogNotFoundMessage::new);
        repository.delete(log);
    }
}
