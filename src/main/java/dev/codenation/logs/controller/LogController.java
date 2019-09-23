package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.LogArchiveDTO;
import dev.codenation.logs.dto.request.LogParameterDTO;
import dev.codenation.logs.dto.response.LogSumaryDTO;
import dev.codenation.logs.exception.message.log.LogCouldNotBeArchivedMessage;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/logs")
public class LogController {

    private LogService logService;

    @Autowired
    private LogMapper mapper;

    @GetMapping("/{logId}")
    public ResponseEntity<Log> findById(@PathVariable UUID logId) throws LogNotFoundMessage {
        Optional<Log> log = logService.findById(logId);

        if (log.isPresent()){
            return ResponseEntity.ok(log.get());
        }else {
            throw new LogNotFoundMessage();
        }
    }

    @GetMapping
    public Page<Log> findAll(LogParameterDTO filter, Pageable page, @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC) Sort sort) {
        Example<Log> logExample = Example.of(mapper.map(filter), ExampleMatcher.matchingAll().withIgnoreCase());
        return logService.findAll(logExample, page, sort);
    }

    @GetMapping("/teste")
    public Object teste(LogParameterDTO filter, Pageable pageable, @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC) Sort sort) {
         Log log = mapper.map(filter);
        return logService.findAllGroupByHash(log,pageable, sort);
    }

    @PostMapping
    public ResponseEntity<Log> post(@RequestBody LogParameterDTO log) {
        Log logMapped = mapper.map(log);
        logService.save(logMapped);
        return new ResponseEntity(logMapped, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Log>> post(@RequestBody List<LogParameterDTO> logs) {
        List<Log> logsMapped = mapper.map(logs);
        logService.save(logsMapped);
        return new ResponseEntity(logsMapped, HttpStatus.CREATED);
    }

    @PatchMapping("/archive/{logId}")
    public ResponseEntity<Log> archive(@PathVariable UUID logId, @Valid LogArchiveDTO filter) throws LogCouldNotBeArchivedMessage {

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
    public ResponseEntity<Log> archive(@PathVariable UUID logId) {
        Optional<Log> log = logService.findById(logId);
        if (log.isPresent()) {
            logService.delete(log.get());
            return ResponseEntity.ok(log.get());
        }
        return ResponseEntity.notFound().build(); //Do a review in status and message returning
    }

}
