package tfg.apitfg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.apitfg.model.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private User user;
    private String isin;
    private boolean buySell;
    private double quantity;
    private boolean processed;
}
