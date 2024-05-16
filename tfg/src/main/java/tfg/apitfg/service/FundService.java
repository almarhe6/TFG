package tfg.apitfg.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tfg.apitfg.commons.BackendExceptionCode;
import tfg.apitfg.commons.BackendHttpException;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.FundHistorical;
import tfg.apitfg.repository.FundHistoricalRepository;

@Service
@RequiredArgsConstructor
public class FundService implements IFundService {
    private final FundHistoricalRepository fundHistoricalRepository;
    private final ICacheService cacheService;

    @Override
    @Cacheable(value = "fundsList")
    public List<Fund> findFunds() {
        try {
            return cacheService.findFundsFromCache();
        } catch (Exception e) {
            return cacheService.findFundsFromDatabase();
        }
    }

    @Override
    public Fund findFund(String isin) {
        try {
            return cacheService.findFundFromCache(isin);
        } catch (Exception e) {
            return cacheService.findFundFromDatabase(isin);
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
