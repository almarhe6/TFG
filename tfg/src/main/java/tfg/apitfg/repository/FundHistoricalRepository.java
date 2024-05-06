package tfg.apitfg.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.FundHistorical;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FundHistoricalRepository extends CrudRepository<FundHistorical,String> {
    @Query("SELECT fh FROM FundsHistorical fh " +
            "WHERE fh.fundsHistoricalPrimaryKey.isin = :isin " +
            "AND fh.fundsHistoricalPrimaryKey.date BETWEEN :startDate AND :endDate")
    List<FundHistorical> findByIsinAndDateBetween(
            @Param("isin") String isin,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
