package tfg.apitfg.controller;

import java.time.LocalDate;
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
    @PostMapping("/{isin}/buy")
    public void buyFund(@RequestAttribute String email, @RequestParam Double quantity, @PathVariable String isin) {
        walletService.tradeFund(email, isin, true, quantity);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{isin}/sell")
    public void sellFunds(@RequestAttribute String email, @RequestParam Double quantity, @PathVariable String isin) {
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

    @GetMapping("/investmentPlan/{isin}")
    public InvestmentPlanDto findInvestmentPlan(@RequestAttribute String email, @PathVariable String isin) {
        return financialMapper.toDto(walletService.findInvestmentPlan(email, isin));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/investmentPlan/create")
    public void createInvestmentPlan(
            @RequestAttribute String email, @PathVariable String isin, Double quantity, LocalDate date) {
        walletService.createInvestmentPlan(email, isin, quantity, date);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/investmentPlan/delete")
    public void deleteInvestmentPlan(@RequestAttribute String email, @PathVariable String isin) {
        walletService.deleteInvestmentPlan(email, isin);
    }
}
