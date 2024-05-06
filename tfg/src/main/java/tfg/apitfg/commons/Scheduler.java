package tfg.apitfg.commons;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tfg.apitfg.repository.FundHistoricalRepository;
import tfg.apitfg.repository.FundRepository;
import tfg.apitfg.repository.InvestmentPlanRepository;
import tfg.apitfg.repository.TransactionRepository;
import tfg.apitfg.repository.WalletRepository;
import tfg.apitfg.service.IFundService;
import tfg.apitfg.service.IUserService;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final IUserService userService;
    private final IFundService fundService;
    private final FundRepository fundRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final FundHistoricalRepository fundHistoricalRepository;
    private final InvestmentPlanRepository investmentPlanRepository;

    @Scheduled(fixedRate = 30000)
    public void completeTransaction(){
        var transactions = transactionRepository.findAllTransactionsPending();
        transactions.forEach(t -> {
            t.setProcessed(true);

        });
    }

    @Scheduled(cron = "0 0 9 * * *") // Se ejecuta a las 9 de la mañana todos los días
    public void applyInvestmentPlans() {
    }
}
