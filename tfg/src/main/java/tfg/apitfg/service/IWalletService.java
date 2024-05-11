package tfg.apitfg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.entity.Transaction;

public interface IWalletService {
    Map<String, Double> findWallet(String email);

    List<Transaction> findTransactions(String email, String isin, LocalDateTime start, LocalDateTime end);

    void createInvestmentPlan(String email, String isin, Double quantity, LocalDate date);

    void deleteInvestmentPlan(String email, String isin);

    InvestmentPlan findInvestmentPlan(String email, String isin);

    List<InvestmentPlan> findInvestmentPlans(String email);

    void tradeFund(String email, String isin, boolean buyOrSell, double quantity);
}
