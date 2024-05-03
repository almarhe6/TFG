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
import tfg.apitfg.model.keys.FundHistoricalPrimaryKey;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="funds_historical")
@IdClass(FundHistoricalPrimaryKey.class)
public class FundHistorical {

    @ManyToOne
    @JoinColumn(name = "isin", referencedColumnName = "isin")
    private Fund fund;

    @Id
    private String isin;

    @Id
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private double price;

    @Column(name = "rent_mont")
    private double rentMont;

    @Column(name = "rent_year")
    private double rentYear;

    @Column(name = "rent_5year")
    private double rent5Year;

    @Column(name = "rent_10year")
    private double rent10Year;

    @Column(name = "rent_20year")
    private double rent20Year;

    @Column(name = "rent")
    private double rent;
}
