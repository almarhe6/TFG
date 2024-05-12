package tfg.apitfg.commons;

import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BackendHttpException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final Map<String, ?> errorData;
    private final BackendExceptionCode backendExceptionCode;

    public BackendHttpException(BackendExceptionCode backendExceptionCode) {
        this.status = backendExceptionCode.getStatus();
        this.code = backendExceptionCode.getDescription();
        this.errorData = Collections.emptyMap();
        this.backendExceptionCode = backendExceptionCode;
    }
}
