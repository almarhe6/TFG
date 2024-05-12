package tfg.apitfg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentPlanDto {
    private String isin;
    private int dayOfMonth;
    private double quantity;
}
