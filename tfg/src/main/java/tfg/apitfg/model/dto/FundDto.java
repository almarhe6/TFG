package tfg.apitfg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundDto {
    private String isin;
    private String name;
    private String company;
    private String index;
}
