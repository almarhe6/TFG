package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="funds_historical")
public class FundHistorical {

    @Id
    @ManyToOne
    @JoinColumn(name = "isin", referencedColumnName = "isin")
    private String isin;

    @Column(name = "day")
    private LocalDateTime date;

    @Column(name = "rent_mont")
    private double rentMonth;

    @Column(name = "rent_year")
    private double rentYear;

    @Column(name = "rent_5year")
    private double rent5Year;

    @Column(name = "rent_10year")
    private double rent10Year;

    @Column(name = "rent_20year")
    private double rent20Year;

    private double rent;
}
