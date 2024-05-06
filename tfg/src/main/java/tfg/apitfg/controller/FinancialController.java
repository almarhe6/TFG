package tfg.apitfg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
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
import tfg.apitfg.model.dto.FundDto;
import tfg.apitfg.model.dto.FundHistoricalDto;
import tfg.apitfg.model.dto.InvestmentPlanDto;
import tfg.apitfg.model.dto.TransactionDto;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.service.IFundService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/finance")
public class FinancialController {
    private final IFundService fundService;
    private final FinancialMapper financialMapper;

    @GetMapping("/funds")
    public List<FundDto> findFunds(){
        return financialMapper.fundsToDto(fundService.findFunds());
    }

    @GetMapping("/funds/{isin}")
    public FundDto getFundInformation(@PathVariable String isin){
        return financialMapper.toDto(fundService.findFund(isin));
    }

    @GetMapping("/funds/{isin}/historical")
    public List<FundHistoricalDto> getFundHistorical(@PathVariable String isin, @RequestParam LocalDate start, @RequestParam LocalDate end){
        return financialMapper.historicalToDto(fundService.findFundHistorical(isin, start, end));
    }

    @GetMapping("/{email}/wallet")
    public Map<String, Double> getWallet(@RequestAttribute String email){
        return fundService.findWallet(email);
    }

    @GetMapping("/transactions/{email}/{isin}")
    public List<TransactionDto> getTransactions(@RequestAttribute String email, @PathVariable String isin, @RequestParam LocalDateTime start, @RequestParam LocalDateTime end){
        return financialMapper.transactionsToDto(fundService.findTransactions(email, isin, start, end));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/funds/{isin}/buy")
    public void buyFund(@RequestAttribute String email, @RequestParam Double quantity, @PathVariable String isin){
        fundService.tradeFund(email, isin, true, quantity);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/funds/{isin}/sell")
    public void sellFunds(@RequestAttribute String email, @RequestParam Double quantity, @PathVariable String isin){
        fundService.tradeFund(email, isin, false, quantity);
    }

    @GetMapping("/investmentPlans")
    public List<InvestmentPlanDto> findInvestmentPlans(@RequestAttribute String email){
        return financialMapper.investmentPlansToDto(fundService.findInvestmentPlans(email));
    }

    @GetMapping("/investmentPlan/{isin}")
    public InvestmentPlanDto findInvestmentPlan(@RequestAttribute String email, @PathVariable String isin){
        return financialMapper.toDto(fundService.findInvestmentPlan(email, isin));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/investmentPlan/create")
    public void createInvestmentPlan(@RequestAttribute String email, @PathVariable String isin, Double quantity, LocalDate date){
        fundService.createInvestmentPlan(email, isin, quantity, date);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/investmentPlan/delete")
    public void deleteInvestmentPlan(@RequestAttribute String email, @PathVariable String isin){
        fundService.deleteInvestmentPlan(email, isin);
    }

}
