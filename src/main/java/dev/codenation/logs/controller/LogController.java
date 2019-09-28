package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.dto.response.LogSumaryResponseDTO;
import dev.codenation.logs.exception.message.log.LogMismatchIdsException;
import dev.codenation.logs.exception.message.log.LogNotFoundException;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/logs")
public class LogController {

    private LogService logService;

    @Autowired
    private LogMapper mapper;

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

    @GetMapping("teste")
    @ResponseStatus(HttpStatus.OK)
    public Page<LogSumaryResponseDTO> teste(LogFilterRequestDTO filter, Pageable page) {

        Log map = mapper.map(filter);
        return logService.findAllGroupByHash(map, page, Sort.unsorted());
    }


    @PatchMapping("/archive/{logId}")
    @ResponseStatus(HttpStatus.OK)
    public Log archive(@PathVariable UUID logId, @RequestBody LogArchiveRequestDTO filter) throws LogNotFoundException, LogMismatchIdsException {
        if(!logId.equals(filter.getId())) {
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
