package tfg.apitfg.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.model.entity.Wallet;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, FundUserPrimaryKey> {
    List<Wallet> findByUser(User user);
}
