package tfg.apitfg.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.Transaction;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, FundUserPrimaryKey> {
    @Query("SELECT t FROM Transaction t " + "WHERE t.user.email = :email "
            + "AND t.fund.isin = :isin "
            + "AND t.effectDateTime BETWEEN :startDate AND :endDate")
    List<Transaction> findByEmailAndIsinAndEffectDatetimeBetween(
            @Param("email") String email,
            @Param("isin") String isin,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t FROM Transaction t WHERE t.processed = false")
    List<Transaction> findAllTransactionsPending();

    @Query("SELECT SUM(t.quantity) FROM Transaction t " + "WHERE t.user.email = :email "
            + "AND t.fund.isin = :isin "
            + "AND t.processed = false")
    Double sumQuantitiesByEmailAndIsinAndNotProcessed(@Param("email") String email, @Param("isin") String isin);
}
