package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name="investment_plans")
@IdClass(FundUserPrimaryKey.class)
public class InvestmentPlan {
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

    @Column(name = "day")
    private LocalDate day;
}
