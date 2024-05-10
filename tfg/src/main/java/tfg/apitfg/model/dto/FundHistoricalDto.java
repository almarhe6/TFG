package tfg.apitfg.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundHistoricalDto {
    private String isin;
    private LocalDateTime date;
    private double rentMonth;
    private double rentYear;
    private double rent5Year;
    private double rent10Year;
    private double rent20Year;
    private double rent;
}
