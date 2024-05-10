package tfg.apitfg.model.keys;

import java.time.LocalDate;
import lombok.Data;

@Data
public class FundHistoricalPrimaryKey {
    private String isin;
    private LocalDate date;
}
