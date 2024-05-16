package tfg.apitfg.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tfg.apitfg.commons.FinancialMapper;
import tfg.apitfg.model.dto.InvestmentPlanDto;
import tfg.apitfg.model.dto.TransactionDto;
import tfg.apitfg.service.IWalletService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/wallet")
public class WalletController {
    private final IWalletService walletService;
    private final FinancialMapper financialMapper;

    @GetMapping()
    public Map<String, Double> getWallet(@RequestAttribute String email) {
        return walletService.findWallet(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/buy/{isin}")
    public void buyFund(@RequestAttribute String email, @PathVariable String isin, @RequestParam Double quantity) {
        walletService.tradeFund(email, isin, true, quantity);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sell/{isin}")
    public void sellFunds(@RequestAttribute String email, @PathVariable String isin, @RequestParam Double quantity) {
        walletService.tradeFund(email, isin, false, quantity);
    }

    @GetMapping("/transactions/{isin}")
    public List<TransactionDto> getTransactions(
            @RequestAttribute String email,
            @PathVariable String isin,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return financialMapper.transactionsToDto(walletService.findTransactions(email, isin, start, end));
    }

    @GetMapping("/investmentPlans")
    public List<InvestmentPlanDto> findInvestmentPlans(@RequestAttribute String email) {
        return financialMapper.investmentPlansToDto(walletService.findInvestmentPlans(email));
    }

    @GetMapping("/investmentPlans/{isin}")
    public InvestmentPlanDto findInvestmentPlan(@RequestAttribute String email, @PathVariable String isin) {
        return financialMapper.toDto(walletService.findInvestmentPlan(email, isin));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/investmentPlans/{isin}/create")
    public void createInvestmentPlan(
            @RequestAttribute String email, @PathVariable String isin, Double quantity, @RequestParam int dayOfMonth) {
        walletService.createInvestmentPlan(email, isin, quantity, dayOfMonth);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/investmentPlans/{isin}/delete")
    public void deleteInvestmentPlan(@RequestAttribute String email, @PathVariable String isin) {
        walletService.deleteInvestmentPlan(email, isin);
    }
}
