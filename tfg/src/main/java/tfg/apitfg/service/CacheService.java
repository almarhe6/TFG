package tfg.apitfg.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import tfg.apitfg.commons.BackendExceptionCode;
import tfg.apitfg.commons.BackendHttpException;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.repository.FundRepository;
import tfg.apitfg.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class CacheService implements ICacheService {
    private final UserRepository userRepository;
    private final FundRepository fundRepository;

    @Override
    @Cacheable(value = "users", key = "#email")
    public User findUserFromCache(String email) {
        return findUserFromDatabase(email);
    }

    @Override
    public User findUserFromDatabase(String email) {
        try {
            var user = userRepository.findById(email);

            if (user.isEmpty()) {
                throw new BackendHttpException(BackendExceptionCode.USER__NOT_FOUND_REPOSITORY_ERROR);
            }

            return user.get();

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.USER__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    @Cacheable(value = "fundsList")
    public List<Fund> findFundsFromCache() {
        return findFundsFromDatabase();
    }

    @Override
    public List<Fund> findFundsFromDatabase() {
        try {
            return fundRepository.findAll();
        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.FUND__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    @Cacheable(value = "fundsDetail", key = "#isin")
    public Fund findFundFromCache(String isin) {
        return findFundFromDatabase(isin);
    }

    @Override
    public Fund findFundFromDatabase(String isin) {
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
}
