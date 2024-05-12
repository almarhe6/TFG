package tfg.apitfg.commons;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Map;

@Builder
@Getter
public class APIError {

    private final HttpStatus status;
    private final String code;
    private Map<String, ?> errorData;
    private String errorType;
    private String errorKey;
    private String errorDescription;
    // TO DO remove this field after proper error handling is perform
    private String errorCode;

    public String getErrorKey() {
        return StringUtils.replace(this.errorKey, "__", ".");
    }
}
