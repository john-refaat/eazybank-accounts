package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Author: john
 * Date: 8/20/26
 */
@Schema(name="Customer", description = "Customer DTO for API requests and responses")
@Data
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "John Doe")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @Schema(description = "Email of the customer", example = "john.doe@example.com")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Phone number of the customer", example = "1234567890")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Schema(description = "Account details of the customer")
    @Valid
    private AccountDto account;
}

