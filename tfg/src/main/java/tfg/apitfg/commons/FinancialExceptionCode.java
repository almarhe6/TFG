package tfg.apitfg.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FinancialExceptionCode {
    USER__CREATE_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user in the database"),
    USER__DELETE_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user in the database"),
    USER__MODIFY_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error modifying user in the database"),
    USER__FINDING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting user from the database"),
    USER__NOT_FOUND_REPOSITORY_ERROR(HttpStatus.NOT_FOUND, "User not found in the database"),
    FUND__FINDING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding funds from the database"),
    FUND__NOT_FOUND_REPOSITORY_ERROR(HttpStatus.NOT_FOUND, "Fund not found in the database"),
    WALLET__FINDING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding wallet from the database"),
    WALLET__NOT_FOUND_REPOSITORY_ERROR(HttpStatus.NOT_FOUND, "Wallet not found in the database");

    private final HttpStatus status;
    private final String description;
}
