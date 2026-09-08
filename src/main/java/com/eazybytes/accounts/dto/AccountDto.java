package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Author: john
 * Date: 8/20/26
 */
@Schema(name="Account", description = "Account details of the customer")
@Data
public class AccountDto {


    @Schema(description = "Account number of the customer", example = "1234567890")
    @Min(value = 1000000000, message = "Account number should be a 10-digit number")
    @Max(value = 9999999999L, message = "Account number should be a 10-digit number")
    private Long accountNumber;

    @Schema(description = "Account type of the customer", example = "SAVINGS")
    @Pattern(regexp = "^(SAVINGS|CURRENT)$", message = "Invalid account type")
    private String accountType;


    @Schema(description = "Bank Branch address of the customer", example = "123 Main Street")
    @NotBlank(message = "Branch address cannot be blank")
    @Size(min = 5, max = 100, message = "Branch address should be between 5 and 100 characters")
    private String branchAddress;
}
