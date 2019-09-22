package dev.codenation.logs.exception.message.model;

import dev.codenation.logs.exception.message.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbstractNotFoundMessage extends AbstractExceptionMessage {

    @Override
    public String getMessage() {
        return "This object does not exists on our database. Please, try again on five minutes. ;)";
    }
}
