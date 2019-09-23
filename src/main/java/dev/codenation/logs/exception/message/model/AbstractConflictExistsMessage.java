package dev.codenation.logs.exception.message.model;

import dev.codenation.logs.exception.message.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AbstractConflictExistsMessage extends AbstractExceptionMessage {

    public AbstractConflictExistsMessage(String message) {
        super(message);
    }

}
