package tfg.apitfg.service;

import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.FundHistorical;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.entity.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IFundService {
    List<Fund> findFunds();
    Fund findFund(String isin);
    Map<String, Double> findWallet(String email);
    List<Transaction>  findTransactions(String email, String isin, LocalDateTime start, LocalDateTime end);
    List<FundHistorical> findFundHistorical(String isin, LocalDate start, LocalDate end);
    void tradeFund(String email, String isin, boolean buyOrSell, double quantity);
    void createInvestmentPlan(String email, String isin, Double quantity, LocalDateTime date);
    void deleteInvestmentPlan(String email, String isin);
    InvestmentPlan findInvestmentPlan(String email, String isin);
    List<InvestmentPlan> findInvestmentPlans(String email);
}
