package tfg.apitfg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.Wallet;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

import java.util.List;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, FundUserPrimaryKey> {
    List<Wallet> findByEmail(String email);
}
