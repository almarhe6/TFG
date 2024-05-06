package tfg.apitfg.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.Transaction;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, FundUserPrimaryKey> {
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.fundUserPrimaryKey.email = :email " +
            "AND t.fundUserPrimaryKey.isin = :isin " +
            "AND t.effectDatetime BETWEEN :startDate AND :endDate")
    List<Transaction> findByEmailAndIsinAndEffectDatetimeBetween(
            @Param("email") String email,
            @Param("isin") String isin,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t FROM Transaction t WHERE t.processed = false")
    List<Transaction> findAllTransactionsPending();
}
