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
    TRANSACTIONS__FINDING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding transactions from the database"),
    TRANSACTIONS__SAVING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving transaction from the database"),
    HISTORICAL__FINDING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding transactions from the database"),
    CREDIT__NOT_ENOUGH_CREDIT_ERROR(HttpStatus.PAYMENT_REQUIRED, "Not enough credit to buy"),
    INVESTMENT_PLAN__SAVING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving investment plan from the database"),
    INVESTMENT_PLAN__NOT_FOUND_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding investment plan from the database"),
    INVESTMENT_PLAN__ALREADY_EXISTS_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding investment plan from the database"),
    INVESTMENT_PLAN__DELETING_REPOSITORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting investment plan from the database"),
    SCHEDULER__ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error in Scheduler");

    private final HttpStatus status;
    private final String description;
}
