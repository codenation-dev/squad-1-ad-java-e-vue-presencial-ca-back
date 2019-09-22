package dev.codenation.logs.exception.message.model;

import dev.codenation.logs.exception.message.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AbstractCouldNotExecuteActionDelete extends AbstractExceptionMessage {

    @Override
    public String getMessage() {
        return "Hey you can not delete this. But if you ask sweetly, maybe...";
    }
}
