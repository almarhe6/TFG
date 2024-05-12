package tfg.apitfg.commons;

import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.util.StringUtils;
import tfg.apitfg.model.dto.FundDto;
import tfg.apitfg.model.dto.FundHistoricalDto;
import tfg.apitfg.model.dto.InvestmentPlanDto;
import tfg.apitfg.model.dto.TransactionDto;
import tfg.apitfg.model.dto.UserDto;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.FundHistorical;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.entity.Transaction;
import tfg.apitfg.model.entity.User;

@Mapper(componentModel = "spring")
public interface FinancialMapper {
    FundDto toDto(Fund fund);

    default List<String> fundsToDto(List<Fund> funds) {
        var fundsList = new ArrayList<String>();
        funds.stream().filter(f -> StringUtils.hasLength(f.getIsin())).forEach(f -> fundsList.add(f.getIsin()));
        return fundsList;
    }

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    default FundHistoricalDto toDto(FundHistorical fundHistorical) {
        return FundHistoricalDto.builder()
                .date(fundHistorical.getDate())
                .isin(fundHistorical.getFund().getIsin())
                .price(fundHistorical.getPrice())
                .rentDay(fundHistorical.getRentDay())
                .rentMonth(fundHistorical.getRentMont())
                .rentYear(fundHistorical.getRentYear())
                .rent5Year(fundHistorical.getRent5Year())
                .rent10Year(fundHistorical.getRent10Year())
                .rent20Year(fundHistorical.getRent20Year())
                .build();
    }

    List<FundHistoricalDto> historicalToDto(List<FundHistorical> fundHistoricals);

    default TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .email(transaction.getUser().getEmail())
                .isin(transaction.getFund().getIsin())
                .buySell(transaction.isBuySell())
                .effectDateTime(transaction.getEffectDateTime())
                .processed(transaction.isProcessed())
                .quantity(transaction.getQuantity())
                .build();
    }

    List<TransactionDto> transactionsToDto(List<Transaction> transaction);

    default InvestmentPlanDto toDto(InvestmentPlan investmentPlan) {
        return InvestmentPlanDto.builder()
                .dayOfMonth(investmentPlan.getDayOfMonth())
                .isin(investmentPlan.getFund().getIsin())
                .quantity(investmentPlan.getQuantity())
                .build();
    }

    List<InvestmentPlanDto> investmentPlansToDto(List<InvestmentPlan> investmentPlans);
}
