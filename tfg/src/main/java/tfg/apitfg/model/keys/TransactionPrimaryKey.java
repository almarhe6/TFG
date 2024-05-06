package tfg.apitfg.model.keys;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionPrimaryKey {
    private String email;
    private String isin;
    private LocalDateTime effectDateTime;
}
