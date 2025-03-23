package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Accounts",
    description = "Schema to hold Account information"
)
public class AccountsDTO {

    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
    @Schema(description = "Account number of Huy Cao account", example = "1234567890")
    private Long accountNumber;

    @NotEmpty(message = "Account type should not be empty")
    @Schema(description = "Account type of Huy Cao account", example = "Savings")
    private String accountType;

    @NotEmpty(message = "Branch address should not be empty")
    @Schema(description = "Branch address of Huy Cao account", example = "Nassoviastraße 8 Langen")
    private String branchAddress;

}
