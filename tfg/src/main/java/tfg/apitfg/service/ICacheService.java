package tfg.apitfg.service;

import java.util.List;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.User;

public interface ICacheService {

    User findUserFromCache(String email);

    User findUserFromDatabase(String email);

    List<Fund> findFundsFromCache();

    List<Fund> findFundsFromDatabase();

    Fund findFundFromCache(String isin);

    Fund findFundFromDatabase(String isin);
}
