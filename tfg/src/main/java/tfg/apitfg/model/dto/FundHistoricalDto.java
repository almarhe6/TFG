package tfg.apitfg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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