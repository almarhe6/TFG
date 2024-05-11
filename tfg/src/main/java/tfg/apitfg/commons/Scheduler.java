package tfg.apitfg.commons;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
    @Scheduled(fixedRate = 30000)
    public void completeTransaction() {
        try {
            var newWallet = new ArrayList<Wallet>();

            var transactions = transactionRepository.findAllTransactionsPending();
            transactions.forEach(t -> {
                t.setProcessed(true);
                var walletOptional = walletRepository.findById(FundUserPrimaryKey.builder()
                        .isin(t.getIsin())
                        .email(t.getEmail())
                        .build());
                if (walletOptional.isEmpty()) {
                    newWallet.add(Wallet.builder()
                            .isin(t.getIsin())
                            .email(t.getEmail())
                            .quantity(t.getQuantity())
                            .fund(fundService.findFund(t.getIsin()))
                            .user(userService.findUser(t.getEmail()))
                            .build());
                } else {
                    var wallet = walletOptional.get();
                    wallet.setQuantity(
                            wallet.getQuantity() == null ? t.getQuantity() : wallet.getQuantity() + t.getQuantity());
                    newWallet.add(wallet);
                }
            });

            walletRepository.saveAll(newWallet);

        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.SCHEDULER__ERROR);
        }
    }

    // Compra de acuerdo a los planes de inversion de los usuarios
    @Scheduled(cron = "0 0 9 * * *") // Se ejecuta a las 9 de la mañana todos los días
    public void applyInvestmentPlans() {
        try {
            var investmentPlans = investmentPlanRepository.findAll();
            var today = LocalDate.now();

            investmentPlans.stream().filter(iv -> today.equals(iv.getDay())).forEach(iv -> {
                walletService.tradeFund(iv.getEmail(), iv.getIsin(), true, iv.getQuantity());
            });
        } catch (DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.SCHEDULER__ERROR);
        }
    }
}
