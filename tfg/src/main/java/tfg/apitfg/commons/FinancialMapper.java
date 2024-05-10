package tfg.apitfg.commons;

import java.util.List;
import org.mapstruct.Mapper;
import tfg.apitfg.model.dto.FundDto;
import tfg.apitfg.model.dto.FundHistoricalDto;
import tfg.apitfg.model.dto.InvestmentPlanDto;
import tfg.apitfg.model.dto.TransactionDto;
import tfg.apitfg.model.dto.UserDto;
import tfg.apitfg.model.dto.WalletDto;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.FundHistorical;
import tfg.apitfg.model.entity.InvestmentPlan;
import tfg.apitfg.model.entity.Transaction;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.model.entity.Wallet;

@Mapper(componentModel = "spring")
public interface FinancialMapper {
    FundDto toDto(Fund fund);

    Fund toEntity(FundDto fundDto);

    List<FundDto> fundsToDto(List<Fund> fund);

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    FundHistoricalDto toDto(FundHistorical fundHistorical);

    FundHistorical toEntity(FundHistoricalDto fundHistoricalDto);

    List<FundHistoricalDto> historicalToDto(List<FundHistorical> fundHistoricals);

    WalletDto toDto(Wallet wallet);

    Wallet toEntity(WalletDto walletDto);

    TransactionDto toDto(Transaction transaction);

    Transaction toEntity(TransactionDto transactionDto);

    List<TransactionDto> transactionsToDto(List<Transaction> transaction);

    InvestmentPlanDto toDto(InvestmentPlan investmentPlan);

    InvestmentPlan toEntity(InvestmentPlanDto investmentPlanDto);

    List<InvestmentPlanDto> investmentPlansToDto(List<InvestmentPlan> investmentPlans);
}
