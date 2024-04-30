package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="funds_historical")
public class Wallet {
    @EmbeddedId
    private FundUserPrimaryKey id;

    @Column(name = "quantity")
    private double quantity;
}
