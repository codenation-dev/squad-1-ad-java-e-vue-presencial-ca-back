package dev.codenation.logs.core.exception.handler;

import dev.codenation.logs.core.exception.message.AbstractException;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@SuppressWarnings("ConstantConditions")
@Order
public abstract class AbstractExceptionHandler<T extends AbstractException> extends ResponseEntityExceptionHandler {

    private T message;

}
