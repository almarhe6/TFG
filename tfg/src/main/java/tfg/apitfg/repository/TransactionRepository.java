package tfg.apitfg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.Transaction;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, FundUserPrimaryKey> {
}
