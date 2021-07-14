package org.mql.gestionsalle.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ReservationCouldNotExecuted extends RuntimeException {

    public ReservationCouldNotExecuted(String message) {
        super(message);
    }
}
