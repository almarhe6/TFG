package tfg.apitfg.model.keys;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.apitfg.model.entity.Fund;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundHistoricalPrimaryKey {
    private Fund fund;
    private LocalDate date;
}
