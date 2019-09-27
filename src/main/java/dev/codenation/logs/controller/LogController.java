package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.exception.message.log.LogCouldNotBeArchivedMessage;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
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
    public Log findById(@PathVariable UUID logId) throws LogNotFoundMessage {
        return logService.findById(logId).orElseThrow(LogNotFoundMessage::new);
    }

    @GetMapping
    public Page<Log> findAll(LogFilterRequestDTO filter, Pageable page) {
        return logService.findAll(filter, page);
    }


    @PatchMapping("/archive/{logId}")
    public Log archive(@PathVariable UUID logId, @Valid LogArchiveRequestDTO filter) throws LogCouldNotBeArchivedMessage, LogNotFoundMessage {
        return logService.archiveLogByIdOrElseThrowError(logId,filter);
    }

    @DeleteMapping("/{logId}")
    public HttpStatus delete(@PathVariable UUID logId) throws LogNotFoundMessage {
        logService.deleteOrElseThrowError(logId);
        return HttpStatus.OK;
    }

}
