package tfg.apitfg.commons;

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

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinancialMapper {
    FundDto toDto(Fund fund);
    List<FundDto> toDto(List<Fund> fund);
    Fund toEntity(FundDto fundDto);

    UserDto toDto(User user);
    User toEntity(UserDto userDto);

    FundHistoricalDto toDto(FundHistorical fundHistorical);
    FundHistorical toEntity(FundHistoricalDto fundHistoricalDto);

    WalletDto toDto(Wallet wallet);
    Wallet toEntity(WalletDto walletDto);

    TransactionDto toDto(Transaction transaction);
    Transaction toEntity(TransactionDto transactionDto);

    InvestmentPlanDto toDto(InvestmentPlan investmentPlan);
    InvestmentPlan toEntity(InvestmentPlanDto investmentPlanDto);
}
