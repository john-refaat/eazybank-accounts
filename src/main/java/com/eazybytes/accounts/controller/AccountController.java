package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.AccountsContactInfo;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Author: john
 * Date: 8/22/26
 */
@Tag(
        name = "CRUD REST API for Accounts",
        description = "CRUD REST API for Accounts for Eazy Bank"
)
@RestController
@RequestMapping("/api/v1/account")
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final AccountsContactInfo accountsContactInfo;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(summary = "Create a new account", description = "Create a new account for a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseDto createAccount(@RequestBody @Valid CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return new ResponseDto("201", "Account Created!");
    }

    @Operation(summary = "Get account details", description = "Get account details for a customer by phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account details retrieved successfully",
            content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{phoneNumber}")
    public CustomerDto getAccount(@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format")
                                      @PathVariable String phoneNumber) {
        return accountService.getAccount(phoneNumber);
    }

    @Operation(summary = "Update an existing account", description = "Update an existing account for a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDto updateAccount(@RequestBody @Valid CustomerDto customerDto) {
        accountService.updateAccount(customerDto);
        return new ResponseDto("200", "Account Updated!");
    }

    @Operation(summary = "Delete an existing account", description = "Delete an existing account for a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{phoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDto deleteAccount(@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format")
                                      @PathVariable String phoneNumber) {
        accountService.deleteAccount(phoneNumber);
        return new ResponseDto("200", "Account Deleted!");
    }


    @Operation(summary = "Get build info", description = "Get build info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Build info retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/build-info")
    public String getBuildInfo() {
        return buildVersion;
    }

    @GetMapping("/contact-info")
    public AccountsContactInfo getContactInfo() {
        return accountsContactInfo;
    }
}
