package tfg.apitfg.commons;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@Builder
@Getter
public class APIError {

    private final HttpStatus status;
    private final String code;
    private Map<String, ?> errorData;
    private String errorType;
    private String errorKey;
    private String errorDescription;
    private String errorCode;

    public String getErrorKey() {
        return StringUtils.replace(this.errorKey, "__", ".");
    }
}
