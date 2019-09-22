package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.exception.message.log.LogCouldNotBeArchivedMessage;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.parameter.LogArchiveParameter;
import dev.codenation.logs.parameter.LogFilter;
import dev.codenation.logs.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Log> findAll(LogFilter filter, @RequestParam(required = false) Sort sort) {
        Example<Log> logExample = Example.of(mapper.map(filter));
        return logService.findAll(logExample, sort);
    }

    @PatchMapping("/archive/{logId}")
    public ResponseEntity<Log> archive(@PathVariable UUID logId, @Valid LogArchiveParameter filter) throws LogCouldNotBeArchivedMessage {

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

}
