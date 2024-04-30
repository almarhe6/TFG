package tfg.apitfg.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
    private String id;
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
