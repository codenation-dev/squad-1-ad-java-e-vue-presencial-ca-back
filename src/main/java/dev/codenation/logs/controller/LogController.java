package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.exception.message.log.LogCouldNotBeArchivedMessage;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/logs")
public class LogController {

    private LogService logService;

    @Autowired
    private LogMapper mapper;

    @GetMapping("/{logId}")
    public Log findById(@PathVariable UUID logId) throws LogNotFoundMessage {
        return logService.findById(logId).orElseThrow(LogNotFoundMessage::new);
    }

    @GetMapping
    public Page<Log> findAll(LogFilterRequestDTO filter, Pageable page, @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC) Sort sort) {
        Example<Log> logExample = Example.of(mapper.map(filter), ExampleMatcher.matchingAll().withIgnoreCase());
        return logService.findAll(logExample, page);
    }

    @PatchMapping("/archive/{logId}")
    public ResponseEntity<Log> archive(@PathVariable UUID logId, @Valid LogArchiveRequestDTO filter) throws LogCouldNotBeArchivedMessage {

        //Todo do Todo, mudar para classe service essa validação.
        //ToDo return a message warning of mismatch ids
        if (logId != filter.getId())
            return ResponseEntity.badRequest().build();

        Optional<Log> log = logService.findById(logId);
        if (log.isPresent()) {
            Log aux = log.get();
            aux.setArchived(filter.getArchived());

            User user = new User();
            user.setId(filter.getUserId());

            aux.setArchivedBy(user);
            aux.setArchivedAt(LocalDateTime.now());

            return ResponseEntity.ok(aux);
        }else{
            throw new LogCouldNotBeArchivedMessage();
        }
    }

    @DeleteMapping("/{logId}")
    public Log delete(@PathVariable UUID logId) throws LogNotFoundMessage {
        Optional<Log> log = logService.findById(logId);
        if (log.isPresent()) {
            logService.delete(log.get());
        }
        return log.orElseThrow(LogNotFoundMessage::new);
    }

}
