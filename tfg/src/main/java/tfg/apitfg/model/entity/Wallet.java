package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@IdClass(FundUserPrimaryKey.class)
public class Wallet {
    @Id
    @ManyToOne
    @JoinColumn(name = "document_number", referencedColumnName = "document_number")
    private String documentNumber;

    @Id
    @ManyToOne
    @JoinColumn(name = "isin", referencedColumnName = "isin")
    private String isin;

    @Column(name = "quantity")
    private double quantity;
}
