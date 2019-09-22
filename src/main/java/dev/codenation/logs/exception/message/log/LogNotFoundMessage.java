package dev.codenation.logs.exception.message.log;

import dev.codenation.logs.exception.message.model.AbstractNotFoundMessage;

public class LogNotFoundMessage extends AbstractNotFoundMessage {

    @Override
    public String getMessage() {
        return "That log was not found on our base, take a coffee and relax, maybe it will be found later...";
    }

}
