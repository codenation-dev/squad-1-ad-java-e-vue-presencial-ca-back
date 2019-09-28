package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.dto.response.LogSumaryResponseDTO;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.repository.LogRepository;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LogService extends AbstractService<LogRepository, Log, UUID> {

    @Autowired
    private LogRepository repository;

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private LogMapper mapper;

    @Autowired
    public LogService(LogRepository repository) {
        super(repository);
    }

    public Page<LogSumaryResponseDTO> findAllGroupByHash(Log log, Pageable pageable, Sort sort) {

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return repository.findAllSumarized(log.getHash(),
                log.getLogDetail().getMessage(),
                log.getLogDetail().getDetails(),
                log.getLogDetail().getSeverity(),
                log.getOrigin().getEnvironment(),
                log.getOrigin().getOrigin(),
                log.getReportedBy().getId(),
                pageable);
    }

    public Page<Log> findAll(LogFilterRequestDTO filter, Pageable pageable) {
        Example<Log> logExample = Example.of(mapper.map(filter), ExampleMatcher.matchingAll().withIgnoreCase());
        return repository.findAll(logExample, pageable);
    }

    public Optional<Log> archiveLogById(UUID logId, LogArchiveRequestDTO filter) {
        return repository.findById(logId).map(l -> setArchivedLogAndSave(filter, l));
    }

    private Log setArchivedLogAndSave(LogArchiveRequestDTO filter, Log log) {
        log.setArchived(filter.getArchived());
        log.setId(filter.getUserId());
        log.setArchivedBy(repositoryUser.getOne(filter.getUserId()));
        log.setArchivedAt(LocalDateTime.now());
        return repository.saveAndFlush(log);
    }

    public Optional<Log> delete(UUID logId) {
        return repository.findById(logId).map(l -> {
            repository.delete(l);
            return l;
        });
    }
}
