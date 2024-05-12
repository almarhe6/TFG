package tfg.apitfg.model.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundHistoricalDto {
    private String isin;
    private LocalDate date;
    private Double price;
    private Double rentDay;
    private Double rentMonth;
    private Double rentYear;
    private Double rent5Year;
    private Double rent10Year;
    private Double rent20Year;
}
