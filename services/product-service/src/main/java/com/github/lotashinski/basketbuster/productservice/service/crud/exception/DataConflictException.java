package com.github.lotashinski.basketbuster.productservice.service.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Data conflict")
public class DataConflictException extends RuntimeException {

    public DataConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}
