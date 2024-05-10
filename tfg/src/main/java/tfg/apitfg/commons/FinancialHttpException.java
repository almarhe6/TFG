package tfg.apitfg.commons;

import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FinancialHttpException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final Map<String, ?> errorData;
    private final FinancialExceptionCode financialExceptionCode;

    public FinancialHttpException(FinancialExceptionCode financialExceptionCode) {
        this.status = financialExceptionCode.getStatus();
        this.code = financialExceptionCode.getDescription();
        this.errorData = Collections.emptyMap();
        this.financialExceptionCode = financialExceptionCode;
    }
}
