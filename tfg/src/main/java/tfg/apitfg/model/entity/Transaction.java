package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import tfg.apitfg.model.keys.TransactionPrimaryKey;

@Data
@Entity
@Builder
@Table(name = "transactions")
@IdClass(TransactionPrimaryKey.class)
public class Transaction {
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

    @Id
    @Column(name = "effect_datetime")
    private LocalDateTime effectDateTime;

    @Column(name = "buy_sell")
    private boolean buySell;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "processed")
    private boolean processed;
}
