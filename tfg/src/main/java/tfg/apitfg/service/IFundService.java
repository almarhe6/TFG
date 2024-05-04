package tfg.apitfg.service;

import tfg.apitfg.model.entity.Fund;

import java.util.List;
import java.util.Map;

public interface IFundService {
    List<Fund> findFunds();
    Fund findFund(String isin);
    Map<String, Double> findWallet(String email);
}
