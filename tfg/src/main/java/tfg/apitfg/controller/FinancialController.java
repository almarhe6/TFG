package tfg.apitfg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/finance")
public class FinancialController {

    @GetMapping("/funds")
    public Object getFunds(){
        return null;
    }

    @GetMapping("/funds/{isin}")
    public Object getFundInformation(){
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

    @GetMapping("/wallet")
    public Object getWallet(){
        return null;
    }

    @GetMapping("/transactions")
    public Object getTransactions(){
        return null;
    }
}
