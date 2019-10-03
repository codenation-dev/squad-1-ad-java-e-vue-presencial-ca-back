package dev.codenation.logs.api.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.core.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.core.dto.request.LogCreationDTO;
import dev.codenation.logs.core.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.core.dto.response.LogResponseDTO;
import dev.codenation.logs.core.dto.response.LogSumaryResponseDTO;
import dev.codenation.logs.core.exception.message.log.LogMismatchIdsException;
import dev.codenation.logs.core.exception.message.log.LogNotFoundException;
import dev.codenation.logs.core.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/{logId}")
    public LogResponseDTO findById(@PathVariable UUID logId) throws LogNotFoundException {
        return logService.findOneById(logId);
    }
    @GetMapping("/sumarized")
    public Page<LogSumaryResponseDTO> findAllSumarized(LogFilterRequestDTO filter, Pageable page) {
        return logService.findAllGroupByHash(filter, page);
    }

    @PostMapping
    public Log create(@RequestBody LogCreationDTO log){
        return logService.save(log);
    }


    @PatchMapping("/archive/{logId}")
    public Log archive(@PathVariable UUID logId, @RequestBody LogArchiveRequestDTO filter) throws LogNotFoundException, LogMismatchIdsException {
        if (!logId.equals(filter.getId())) {
            throw new LogMismatchIdsException();
        }
        return logService.archiveLogById(logId, filter).orElseThrow(LogNotFoundException::new);
    }

    @DeleteMapping("/{logId}")
    public Log delete(@PathVariable UUID logId) throws LogNotFoundException {
        return logService.delete(logId).orElseThrow(LogNotFoundException::new);
    }
}
