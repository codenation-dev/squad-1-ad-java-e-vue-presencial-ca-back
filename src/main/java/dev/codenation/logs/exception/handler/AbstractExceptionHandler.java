package dev.codenation.logs.exception.handler;

import dev.codenation.logs.exception.message.AbstractExceptionMessage;
import dev.codenation.logs.exception.message.model.AbstractNotFoundMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@SuppressWarnings("ConstantConditions")
@Order
public abstract class AbstractExceptionHandler<T extends AbstractExceptionMessage> extends ResponseEntityExceptionHandler {

    private T message;

    @ExceptionHandler(AbstractNotFoundMessage.class)
    private ResponseEntity<Object> entityNotFoundException(){
        return new ResponseEntity<>(message.getMessage(),null);
    }

}
