package tfg.apitfg.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.model.keys.FundUserPrimaryKey;

@Repository
public interface InvestmentPlanRepository extends CrudRepository<InvestmentPlan, FundUserPrimaryKey> {
    List<InvestmentPlan> findAllByUser(User user);

    @Override
    List<InvestmentPlan> findAll();
}
