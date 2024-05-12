package tfg.apitfg.commons;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.entity.Wallet;
import tfg.apitfg.model.keys.FundUserPrimaryKey;
import tfg.apitfg.repository.InvestmentPlanRepository;
import tfg.apitfg.repository.TransactionRepository;
import tfg.apitfg.repository.WalletRepository;
import tfg.apitfg.service.IFundService;
import tfg.apitfg.service.IUserService;
import tfg.apitfg.service.IWalletService;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final IUserService userService;
    private final IFundService fundService;
    private final IWalletService walletService;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final InvestmentPlanRepository investmentPlanRepository;

    // Itera sobre las transacciones pendientes, las marca como procesadas y actualiza las carteras
    @Transactional
    @Scheduled(fixedRate = 60000)
    public void completeTransaction() {
        try {

            var transactions = transactionRepository.findAllTransactionsPending();
            if (!transactions.isEmpty()) {
                transactions.forEach(t -> {
                    var walletOptional = walletRepository.findById(FundUserPrimaryKey.builder()
                            .fund(t.getFund())
                            .user(t.getUser())
                            .build());
                    if (walletOptional.isEmpty()) {
                        // Aqui siempre es en un escenario de compra, ya que el flujo de venta impide vender un fondo
                        // que no se tiene
                        walletRepository.save(Wallet.builder()
                                .quantity(t.getQuantity())
                                .fund(fundService.findFund(t.getFund().getIsin()))
                                .user(userService.findUser(t.getUser().getEmail()))
                                .build());
                    } else {
                        var wallet = walletOptional.get();
                        if (t.isBuySell()) {
                            // BUYING
                            wallet.setQuantity(
                                    wallet.getQuantity() == null
                                            ? t.getQuantity()
                                            : wallet.getQuantity() + t.getQuantity());
                            walletRepository.save(wallet);
                        } else {
                            // SELLING
                            if (wallet.getQuantity() - t.getQuantity() == 0) {
                                walletRepository.delete(wallet);
                            } else {
                                wallet.setQuantity(wallet.getQuantity() - t.getQuantity());
                                walletRepository.save(wallet);
                            }
                        }
                    }

                    t.setProcessed(true);
                    transactionRepository.save(t);
                });
            }

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.SCHEDULER__ERROR);
        }
    }

    // Compra de acuerdo a los planes de inversion de los usuarios
    @Transactional
    @Scheduled(cron = "0 0 9 * * *") // Se ejecuta a las 9 de la mañana todos los días
    public void applyInvestmentPlans() {
        try {
            var investmentPlans = investmentPlanRepository.findAll();

            investmentPlans.stream().filter(this::isDayToBuy).forEach(iv -> {
                walletService.tradeFund(iv.getUser().getEmail(), iv.getFund().getIsin(), true, iv.getQuantity());
            });

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.SCHEDULER__ERROR);
        }
    }

    private boolean isDayToBuy(InvestmentPlan investmentPlan) {
        var dayOfInvestment = investmentPlan.getDayOfMonth();
        var dayOfMonth = LocalDate.now().getDayOfMonth();
        var daysInMonth = YearMonth.of(
                        LocalDate.now().getYear(), LocalDate.now().getMonth())
                .lengthOfMonth();

        if (dayOfInvestment > daysInMonth && daysInMonth == dayOfMonth) {
            return true;
        }

        return dayOfInvestment == dayOfMonth;
    }
}
