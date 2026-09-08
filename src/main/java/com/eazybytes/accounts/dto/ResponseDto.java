package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author: john
 * Date: 8/20/26
 */
@Schema(name="Response", description = "Response DTO for API responses")
@Data
@AllArgsConstructor
public class ResponseDto {

    private String statusCode;
    private String message;
}
