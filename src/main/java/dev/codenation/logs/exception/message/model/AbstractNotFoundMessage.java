package dev.codenation.logs.exception.message.model;

import dev.codenation.logs.exception.message.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbstractNotFoundMessage extends AbstractExceptionMessage {

    public AbstractNotFoundMessage(String message) {
        super(message);
    }
}
