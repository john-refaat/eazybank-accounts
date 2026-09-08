package com.eazybytes.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: john
 * Date: 8/22/26
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
