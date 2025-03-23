package com.banking.accounts.controllers;

import com.banking.accounts.constants.AccountsConstants;
import com.banking.accounts.dto.CustomerDTO;
import com.banking.accounts.dto.ErrorResponseDTO;
import com.banking.accounts.dto.ResponseDTO;
import com.banking.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD REST APIs for Accounts in Banking Microservice",
    description = "CRUD REST APIs to CREATE, UPDATE, FETCH, and DELETE Accounts in Banking Microservice"
)
@RestController
@RequestMapping(path = "/api", produces = "application/json")
@Validated
@AllArgsConstructor
public class AccountsController {

    private IAccountsService IAccountsService;

    @Operation(
        summary = "Create Account REST API in Banking Microservice",
        description = "REST API to create new Account in Banking Microservice"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        IAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
        summary = "Fetch Account REST API in Banking Microservice",
        description = "REST API to fetch Account details in Banking Microservice"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                       @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
                                       String mobileNumber) {
        CustomerDTO customerDTO = IAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @Operation(
        summary = "Update Account REST API in Banking Microservice",
        description = "REST API to update Account details in Banking Microservice"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
        ),
        @ApiResponse(
            responseCode = "417",
            description = "Expectation failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = IAccountsService.updateAccount(customerDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
        summary = "Delete Account REST API in Banking Microservice",
        description = "REST API to delete Account details in Banking Microservice"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
        ),
        @ApiResponse(
            responseCode = "417",
            description = "Expectation failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDTO.class)
            )
        )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@RequestParam
                                       @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
                                       String mobileNumber) {
        boolean isDeleted = IAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
