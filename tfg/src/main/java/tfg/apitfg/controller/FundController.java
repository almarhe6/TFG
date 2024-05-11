package tfg.apitfg.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tfg.apitfg.commons.FinancialMapper;
import tfg.apitfg.model.dto.FundDto;
import tfg.apitfg.model.dto.FundHistoricalDto;
import tfg.apitfg.service.IFundService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/funds")
public class FundController {
    private final IFundService fundService;
    private final FinancialMapper financialMapper;

    @GetMapping()
    public List<FundDto> findFunds() {
        return financialMapper.fundsToDto(fundService.findFunds());
    }

    @GetMapping("/{isin}")
    public FundDto getFundInformation(@PathVariable String isin) {
        return financialMapper.toDto(fundService.findFund(isin));
    }

    @GetMapping("/{isin}/historical")
    public List<FundHistoricalDto> getFundHistorical(
            @PathVariable String isin, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        return financialMapper.historicalToDto(fundService.findFundHistorical(isin, start, end));
    }
}
