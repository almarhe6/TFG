package tfg.apitfg.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tfg.apitfg.commons.BackendExceptionCode;
import tfg.apitfg.commons.BackendHttpException;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.FundHistorical;
import tfg.apitfg.repository.FundHistoricalRepository;
import tfg.apitfg.repository.FundRepository;
import tfg.apitfg.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class FundService implements IFundService {
    private final FundRepository fundRepository;
    private final TransactionRepository transactionRepository;
    private final FundHistoricalRepository fundHistoricalRepository;

    @Override
    public List<Fund> findFunds() {
        try {
            return fundRepository.findAll();
        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.FUND__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    public Fund findFund(String isin) {
        try {
            System.out.println(isin);
            var fund = fundRepository.findById(isin);

            if (fund.isEmpty()) {
                throw new BackendHttpException(BackendExceptionCode.FUND__NOT_FOUND_REPOSITORY_ERROR);
            }

            return fund.get();
        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.FUND__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    public List<FundHistorical> findFundHistorical(String isin, LocalDate start, LocalDate end) {
        try {
            return fundHistoricalRepository.findByIsinAndDateBetween(isin, start, end);

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.HISTORICAL__FINDING_REPOSITORY_ERROR);
        }
    }
}
