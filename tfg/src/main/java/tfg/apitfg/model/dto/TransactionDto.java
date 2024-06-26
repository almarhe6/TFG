package tfg.apitfg.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private String email;
    private String isin;
    private LocalDateTime effectDateTime;
    private boolean buySell;
    private double quantity;
    private boolean processed;
}
