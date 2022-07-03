package com.company.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SymbolNotFoundException extends RuntimeException{
    public SymbolNotFoundException(String message) {
        super(message);
    }
}
