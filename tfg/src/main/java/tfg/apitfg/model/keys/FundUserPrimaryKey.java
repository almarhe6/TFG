package tfg.apitfg.model.keys;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FundUserPrimaryKey {
    private String email;
    private String isin;
}
