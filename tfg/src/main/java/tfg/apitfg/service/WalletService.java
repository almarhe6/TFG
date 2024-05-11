package tfg.apitfg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tfg.apitfg.commons.FinancialExceptionCode;
import tfg.apitfg.commons.FinancialHttpException;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.entity.Transaction;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.model.keys.FundUserPrimaryKey;
import tfg.apitfg.repository.InvestmentPlanRepository;
import tfg.apitfg.repository.TransactionRepository;
import tfg.apitfg.repository.WalletRepository;

@Service
@RequiredArgsConstructor
public class WalletService implements IWalletService {
    private final IUserService userService;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final InvestmentPlanRepository investmentPlanRepository;
    private final IFundService fundService;

    private final Random random = new Random();
    private static final Double CREDIT_THRESHOLD = 0.5;

    @Override
    public Map<String, Double> findWallet(String email) {
        try {

            var walletMap = new HashMap<String, Double>();
            var wallet = walletRepository.findByEmail(email);

            wallet.forEach(w -> walletMap.put(w.getIsin(), w.getQuantity()));

            return walletMap;

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.WALLET__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    public List<Transaction> findTransactions(String email, String isin, LocalDateTime start, LocalDateTime end) {
        try {
            return transactionRepository.findByEmailAndIsinAndEffectDatetimeBetween(email, isin, start, end);

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.TRANSACTIONS__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    public void createInvestmentPlan(String email, String isin, Double quantity, LocalDate date) {
        try {
            if (investmentPlanRepository
                    .findById(
                            FundUserPrimaryKey.builder().email(email).isin(isin).build())
                    .isPresent()) {
                throw new FinancialHttpException(
                        FinancialExceptionCode.INVESTMENT_PLAN__ALREADY_EXISTS_REPOSITORY_ERROR);
            }

            investmentPlanRepository.save(InvestmentPlan.builder()
                    .day(date)
                    .quantity(quantity)
                    .email(email)
                    .isin(isin)
                    .fund(fundService.findFund(isin))
                    .user(userService.findUser(email))
                    .build());

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.INVESTMENT_PLAN__SAVING_REPOSITORY_ERROR);
        }
    }

    @Override
    public InvestmentPlan findInvestmentPlan(String email, String isin) {
        try {

            var investmentPlan = investmentPlanRepository.findById(
                    FundUserPrimaryKey.builder().email(email).isin(isin).build());

            if (investmentPlan.isEmpty()) {
                throw new FinancialHttpException(FinancialExceptionCode.INVESTMENT_PLAN__NOT_FOUND_REPOSITORY_ERROR);
            }

            return investmentPlan.get();

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.INVESTMENT_PLAN__SAVING_REPOSITORY_ERROR);
        }
    }

    @Override
    public List<InvestmentPlan> findInvestmentPlans(String email) {
        try {

            return investmentPlanRepository.findAllByEmail(email);

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.INVESTMENT_PLAN__NOT_FOUND_REPOSITORY_ERROR);
        }
    }

    @Override
    public void deleteInvestmentPlan(String email, String isin) {
        try {

            transactionRepository.deleteById(
                    FundUserPrimaryKey.builder().email(email).isin(isin).build());

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.INVESTMENT_PLAN__DELETING_REPOSITORY_ERROR);
        }
    }

    @Override
    public void tradeFund(String email, String isin, boolean buyOrSell, double quantity) {
        try {
            var user = userService.findUser(email);

            if (buyOrSell) {
                checkCredit(user);
            }

            transactionRepository.save(Transaction.builder()
                    .email(email)
                    .isin(isin)
                    .buySell(buyOrSell)
                    .processed(false)
                    .quantity(quantity)
                    .effectDateTime(LocalDateTime.now())
                    .user(user)
                    .fund(fundService.findFund(isin))
                    .build());

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.TRANSACTIONS__SAVING_REPOSITORY_ERROR);
        }
    }

    // Este método comprobaría si el usuario dispone de crédito para comprar el fondo
    private void checkCredit(User user) {
        var randomValue = random.nextDouble();
        if (randomValue > CREDIT_THRESHOLD) {
            throw new FinancialHttpException(FinancialExceptionCode.CREDIT__NOT_ENOUGH_CREDIT_ERROR);
        }
    }
}
