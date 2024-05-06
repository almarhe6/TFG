package tfg.apitfg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.Fund;

import java.util.List;

@Repository
public interface FundRepository extends CrudRepository<Fund, String> {
    @Override
    List<Fund> findAll();
}
