package tfg.apitfg.service;

import java.time.LocalDate;
import java.util.List;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.FundHistorical;

public interface IFundService {
    List<Fund> findFunds();

    Fund findFund(String isin);

    List<FundHistorical> findFundHistorical(String isin, LocalDate start, LocalDate end);
}
