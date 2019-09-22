package dev.codenation.logs.exception.handler.log;

import dev.codenation.logs.exception.handler.AbstractExceptionHandler;
import dev.codenation.logs.exception.message.log.LogCouldNotBeDeletedMessage;
import dev.codenation.logs.exception.message.log.LogNotFoundMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Controller
public class LogExceptionHandler extends AbstractExceptionHandler {

    private LogNotFoundMessage logNotFoundMessage;
    private LogCouldNotBeDeletedMessage logCouldNotBeDeletedMessage;

    @ExceptionHandler(LogNotFoundMessage.class)
    public ResponseEntity logNotFoundException(){
        return new ResponseEntity<>(logNotFoundMessage.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LogCouldNotBeDeletedMessage.class)
    public ResponseEntity<Object> logCannotBeDeleted(){
        return new ResponseEntity<>(logCouldNotBeDeletedMessage.getMessage(),HttpStatus.UNAUTHORIZED);
    }



}
