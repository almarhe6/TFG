package tfg.apitfg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

import java.util.List;

@Repository
public interface InvestmentPlanRepository extends CrudRepository<InvestmentPlan, FundUserPrimaryKey> {
    List<InvestmentPlan> findAllByEmail(String email);
}
