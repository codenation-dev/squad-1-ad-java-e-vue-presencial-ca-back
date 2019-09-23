package dev.codenation.logs.exception.handler.user;

import dev.codenation.logs.exception.handler.AbstractExceptionHandler;
import dev.codenation.logs.exception.message.user.UserExistsMessage;
import dev.codenation.logs.exception.message.user.UserNotFoundMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UserExceptionHandler extends AbstractExceptionHandler {

    private UserExistsMessage userExistsMessage;
    private UserNotFoundMessage userNotFoundMessage;

    @ExceptionHandler(UserNotFoundMessage.class)
    private ResponseEntity<Object> userNotFoundException(){
        return new ResponseEntity<>(userNotFoundMessage.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsMessage.class)
    private ResponseEntity<Object> userExistsException(){
        return new ResponseEntity<>(userExistsMessage.getMessage(), HttpStatus.CONFLICT);
    }

}
