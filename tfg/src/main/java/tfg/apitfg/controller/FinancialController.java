package tfg.apitfg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfg.apitfg.commons.FinancialMapper;
import tfg.apitfg.model.dto.FundDto;
import tfg.apitfg.service.IFundService;

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
        return financialMapper.toDto(fundService.findFunds());
    }

    @GetMapping("/funds/{isin}")
    public FundDto getFundInformation(@PathVariable String isin){
        return financialMapper.toDto(fundService.findFund(isin));
    }

    @GetMapping("/wallet")
    public Map<String, Double> getWallet(){
        return null;
    }


    @GetMapping("/transactions")
    public Object getTransactions(){
        return null;
    }

    @GetMapping("/funds/{isin}/historical")
    public Object getFundHistorical(){
        return null;
    }

    @PostMapping("/funds/{isin}/buy")
    public Object buyFund(){
        return null;
    }

    @PostMapping("/funds/{isin}/sell")
    public Object sellFunds(){
        return null;
    }

    @PostMapping("/investmentPlan/create")
    public Object createInvestmentPlan(){
        return null;
    }

    @PostMapping("/investmentPlan/delete")
    public Object deleteInvestmentPlan(){
        return null;
    }

}
