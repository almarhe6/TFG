package tfg.apitfg.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tfg.apitfg.commons.BackendExceptionCode;
import tfg.apitfg.commons.BackendHttpException;
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
            var wallet = walletRepository.findByUser(userService.findUser(email));

            wallet.forEach(w -> walletMap.put(w.getFund().getIsin(), w.getQuantity()));

            return walletMap;

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.WALLET__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    public List<Transaction> findTransactions(String email, String isin, LocalDateTime start, LocalDateTime end) {
        try {
            return transactionRepository.findByEmailAndIsinAndEffectDatetimeBetween(email, isin, start, end);

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.TRANSACTIONS__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    public void createInvestmentPlan(String email, String isin, Double quantity, int dayOfMonth) {
        try {
            if (dayOfMonth < 1 || dayOfMonth > 31) {
                throw new BackendHttpException(BackendExceptionCode.INVESTMENT_PLAN__INVALID_DAY_OF_MONTH);
            }

            var user = userService.findUser(email);
            var fund = fundService.findFund(isin);

            if (investmentPlanRepository
                    .findById(FundUserPrimaryKey.builder().user(user).fund(fund).build())
                    .isPresent()) {
                throw new BackendHttpException(
                        BackendExceptionCode.INVESTMENT_PLAN__ALREADY_EXISTS_REPOSITORY_ERROR);
            }

            investmentPlanRepository.save(InvestmentPlan.builder()
                    .dayOfMonth(dayOfMonth)
                    .quantity(quantity)
                    .user(user)
                    .fund(fund)
                    .build());

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.INVESTMENT_PLAN__SAVING_REPOSITORY_ERROR);
        }
    }

    @Override
    public InvestmentPlan findInvestmentPlan(String email, String isin) {
        try {
            var user = userService.findUser(email);
            var fund = fundService.findFund(isin);

            var investmentPlan = investmentPlanRepository.findById(
                    FundUserPrimaryKey.builder().user(user).fund(fund).build());

            if (investmentPlan.isEmpty()) {
                throw new BackendHttpException(BackendExceptionCode.INVESTMENT_PLAN__NOT_FOUND_REPOSITORY_ERROR);
            }

            return investmentPlan.get();

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.INVESTMENT_PLAN__SAVING_REPOSITORY_ERROR);
        }
    }

    @Override
    public List<InvestmentPlan> findInvestmentPlans(String email) {
        try {

            return investmentPlanRepository.findAllByUser(userService.findUser(email));

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.INVESTMENT_PLAN__NOT_FOUND_REPOSITORY_ERROR);
        }
    }

    @Override
    public void deleteInvestmentPlan(String email, String isin) {
        try {
            var user = userService.findUser(email);
            var fund = fundService.findFund(isin);
            investmentPlanRepository.deleteById(
                    FundUserPrimaryKey.builder().fund(fund).user(user).build());

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.INVESTMENT_PLAN__DELETING_REPOSITORY_ERROR);
        }
    }

    @Override
    public void tradeFund(String email, String isin, boolean buyOrSell, double quantity) {
        try {
            var user = userService.findUser(email);
            var fund = fundService.findFund(isin);
            // BUYING
            if (buyOrSell) {
                checkCredit(user);
            } else {
                // SELLING
                var wallet = findWallet(email);

                if (!wallet.containsKey(isin)) {
                    throw new BackendHttpException(BackendExceptionCode.SELL__USER_DOESNT_OWN_FUND);
                }
                var accQuantity = transactionRepository.sumQuantitiesByEmailAndIsinAndNotProcessed(email, isin);

                if (accQuantity == null) {
                    accQuantity = 0.0;
                }

                if (wallet.get(isin) - (accQuantity + quantity) < 0) {
                    throw new BackendHttpException(BackendExceptionCode.SELL__INSUFFICIENT_CREDIT);
                }
            }

            transactionRepository.save(Transaction.builder()
                    .buySell(buyOrSell)
                    .processed(false)
                    .quantity(quantity)
                    .effectDateTime(LocalDateTime.now())
                    .user(user)
                    .fund(fund)
                    .build());

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.TRANSACTIONS__SAVING_REPOSITORY_ERROR);
        }
    }

    // Este método comprobaría si el usuario dispone de crédito para comprar el fondo
    private void checkCredit(User user) {
        var randomValue = random.nextDouble();
        if (randomValue > CREDIT_THRESHOLD) {
            throw new BackendHttpException(BackendExceptionCode.CREDIT__NOT_ENOUGH_CREDIT_ERROR);
        }
    }
}
