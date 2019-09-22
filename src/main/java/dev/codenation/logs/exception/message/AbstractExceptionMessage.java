package dev.codenation.logs.exception.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractExceptionMessage extends Exception {

    public String message;

}
