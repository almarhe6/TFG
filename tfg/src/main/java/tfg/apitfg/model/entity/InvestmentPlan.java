package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "investment_plans")
public class InvestmentPlan {

    @EmbeddedId
    private FundUserPrimaryKey id;

    @Column(name = "day")
    private LocalDateTime date;

    private double quantity;
}
