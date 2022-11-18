package io.test.money_transfer.config.validation;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 2887005782241588737L;

    private final String status;

    public ValidationException(final String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST.name();
    }

    public ValidationException(final String message, final String status) {
        super(message);
        this.status = status;
    }
}
