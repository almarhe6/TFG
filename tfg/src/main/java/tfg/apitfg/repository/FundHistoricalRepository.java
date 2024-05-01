package tfg.apitfg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.FundHistorical;

@Repository
public interface FundHistoricalRepository extends CrudRepository<FundHistorical,String> {
}
