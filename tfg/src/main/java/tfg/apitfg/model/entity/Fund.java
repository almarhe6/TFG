package tfg.apitfg.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "funds")
public class Fund {
    @Id
    @Column(name = "isin")
    private String isin;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "index")
    private String index;
}
