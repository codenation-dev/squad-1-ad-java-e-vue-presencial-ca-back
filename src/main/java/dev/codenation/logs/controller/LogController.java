package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.LogArchiveDTO;
import dev.codenation.logs.dto.request.LogFilterDTO;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private LogMapper mapper;

    @GetMapping("/{logId}")
    public ResponseEntity<Log> findById(@PathVariable UUID logId) {
        Optional<Log> log = logService.findById(logId);
        return (log.isPresent()) ? ResponseEntity.ok(log.get()) : ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Log> findAll(LogFilterDTO filter, @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC) Sort sort) {
             Example<Log> logExample = Example.of(mapper.map(filter), ExampleMatcher.matchingAll().withIgnoreCase());
        return logService.findAll(logExample, sort);
    }

    @PatchMapping("/archive/{logId}")
    public ResponseEntity<Log> archive(@PathVariable UUID logId, @Valid LogArchiveDTO filter) {

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
        }
        return ResponseEntity.noContent().build();
    }
}
