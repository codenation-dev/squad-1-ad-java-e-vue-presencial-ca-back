package dev.codenation.logs.exception.handler.log;

import dev.codenation.logs.exception.handler.AbstractExceptionHandler;
import dev.codenation.logs.exception.message.log.LogCouldNotBeArchivedMessage;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class LogExceptionHandler extends AbstractExceptionHandler {

    private LogNotFoundMessage logNotFoundMessage = new LogNotFoundMessage();

    private LogCouldNotBeArchivedMessage logCouldNotBeArchivedMessage = new LogCouldNotBeArchivedMessage();

    @ExceptionHandler(LogNotFoundMessage.class)
    public ResponseEntity<Object> logNotFoundException(){
        return new ResponseEntity<>(logNotFoundMessage.getLocalizedMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LogCouldNotBeArchivedMessage.class)
    public ResponseEntity<Object> logCannotBeArchived(){
        return new ResponseEntity<>(logCouldNotBeArchivedMessage.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
    }



}
