package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.exception.message.log.LogMismatchIdsException;
import dev.codenation.logs.exception.message.log.LogNotFoundException;
import dev.codenation.logs.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/logs")
public class LogController {

    private LogService logService;

    @GetMapping("/{logId}")
    @ResponseStatus(HttpStatus.OK)
    public Log findById(@PathVariable UUID logId) throws LogNotFoundException {
        return logService.findById(logId).orElseThrow(LogNotFoundException::new );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Log> findAll(LogFilterRequestDTO filter, Pageable page) {
        return logService.findAll(filter, page);
    }


    @PatchMapping("/archive/{logId}")
    @ResponseStatus(HttpStatus.OK)
    public Log archive(@PathVariable UUID logId, @Valid LogArchiveRequestDTO filter) throws LogNotFoundException, LogMismatchIdsException {
        if(logId != filter.getId()) {
            throw new LogMismatchIdsException();
        }
        return logService.archiveLogById(logId,filter).orElseThrow(LogNotFoundException::new);
    }

    @DeleteMapping("/{logId}")
    @ResponseStatus(HttpStatus.OK)
    public Log delete(@PathVariable UUID logId) throws LogNotFoundException {
       return logService.delete(logId).orElseThrow(LogNotFoundException::new);
     }
}
