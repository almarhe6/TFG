package tfg.apitfg.model.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class FundUserPrimaryKey {
    @Column(name="document_number")
    private String documentNumber;
    private String isin;
}
