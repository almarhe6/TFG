package tfg.apitfg.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BackendHttpException.class)
    ResponseEntity<APIError> handleBackendException(BackendHttpException e) {
        log.error("BACKEND_ERROR: {}. ", e.getCode(), e);
        var apiError = APIError.builder()
                .errorType("BACKEND")
                .status(e.getStatus())
                .errorKey(e.getBackendExceptionCode().name())
                .errorDescription(e.getBackendExceptionCode().getDescription())
                .errorData(e.getErrorData())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
