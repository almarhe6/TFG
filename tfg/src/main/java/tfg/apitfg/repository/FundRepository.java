package tfg.apitfg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.Fund;

@Repository
public interface FundRepository extends CrudRepository<Fund, String> {
}
