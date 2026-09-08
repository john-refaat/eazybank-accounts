package com.eazybytes.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Author: john
 * Date: 8/20/26
 */
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus httpStatus;
    private String errorMessage;
    private LocalDateTime errorTime;
}
