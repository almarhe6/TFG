package tfg.apitfg.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.apitfg.model.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentPlanDto {
    private User user;
    private String isin;
    private LocalDateTime date;
    private double quantity;
}
