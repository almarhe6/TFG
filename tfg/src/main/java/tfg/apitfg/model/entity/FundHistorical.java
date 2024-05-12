package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import tfg.apitfg.model.keys.FundHistoricalPrimaryKey;

@Data
@Entity
@Table(name = "funds_historical")
@IdClass(FundHistoricalPrimaryKey.class)
public class FundHistorical {

    @Id
    @ManyToOne
    @JoinColumn(name = "isin", referencedColumnName = "isin")
    private Fund fund;

    @Id
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private Double price;

    @Column(name = "rent")
    private Double rentDay;

    @Column(name = "rent_mont")
    private Double rentMont;

    @Column(name = "rent_year")
    private Double rentYear;

    @Column(name = "rent_5year")
    private Double rent5Year;

    @Column(name = "rent_10year")
    private Double rent10Year;

    @Column(name = "rent_20year")
    private Double rent20Year;
}
