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
    USER__RETRIEVE_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving user in the database"),
    USER__NOT_FOUND_REPOSITORY_ERROR(HttpStatus.NOT_FOUND, "User not found in the database");

    private final HttpStatus status;
    private final String description;
}
