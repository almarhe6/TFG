package tfg.apitfg.model.keys;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FundHistoricalPrimaryKey {
    private String isin;
    private LocalDate date;
}
