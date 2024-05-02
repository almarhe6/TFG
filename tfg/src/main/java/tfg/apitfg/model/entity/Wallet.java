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
@Table(name="wallet")
@IdClass(FundUserPrimaryKey.class)
public class Wallet {
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "isin", referencedColumnName = "isin")
    private Fund fund;

    @Id
    private String email;

    @Id
    private String isin;

    @Column(name = "quantity")
    private double quantity;
}
