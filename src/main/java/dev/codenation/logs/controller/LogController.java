package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.filter.LogFilter;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    private LogMapper mapper;

    @GetMapping("/{logId}")
    public ResponseEntity<Log> findById(@PathVariable UUID logId) {
        Optional<Log> log = logService.findById(logId);
        return (log.isPresent()) ? ResponseEntity.ok(log.get()) : ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Log> findAll(@Valid LogFilter filter, Sort sort) {
        Example<Log> logExample = Example.of(mapper.map(filter));
        return logService.findAll(logExample, sort);
    }
}
