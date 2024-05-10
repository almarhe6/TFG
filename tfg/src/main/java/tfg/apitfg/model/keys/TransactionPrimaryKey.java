package tfg.apitfg.model.keys;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransactionPrimaryKey {
    private String email;
    private String isin;
    private LocalDateTime effectDateTime;
}
