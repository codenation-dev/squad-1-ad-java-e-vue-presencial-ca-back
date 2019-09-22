package dev.codenation.logs.exception.message.log;

import dev.codenation.logs.exception.message.model.AbstractCouldNotExecuteActionDelete;

public class LogCouldNotBeDeletedMessage extends AbstractCouldNotExecuteActionDelete {
    @Override
    public String getMessage() {
        return "That log can't be deleted, should you have none permissions to do it.\n\n" +
                "If you pay 50$ for that guy www.github.com/evertonfreire then we could talk again about it rsrs";
    }
}
