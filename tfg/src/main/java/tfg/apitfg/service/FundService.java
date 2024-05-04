package tfg.apitfg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tfg.apitfg.commons.FinancialExceptionCode;
import tfg.apitfg.commons.FinancialHttpException;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.Wallet;
import tfg.apitfg.repository.FundRepository;
import tfg.apitfg.repository.WalletRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class FundService implements IFundService{
    private final FundRepository fundRepository;
    private final WalletRepository walletRepository;

    @Override
    public List<Fund> findFunds() {
        try {
            var fundsIterable = fundRepository.findAll();
            var funds = new ArrayList<Fund>();
            fundsIterable.forEach(funds::add);
            return funds;
        } catch(DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.FUND__FINDING_REPOSITORY_ERROR);
        }
    }

    @Override
    public Fund findFund(String isin){
        try {
            var fund = fundRepository.findById(isin);

            if(fund.isEmpty()){
                throw new FinancialHttpException(FinancialExceptionCode.FUND__NOT_FOUND_REPOSITORY_ERROR);
            }

            return fund.get();
        } catch(DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.FUND__FINDING_REPOSITORY_ERROR);
        }
    }


    @Override
    public Map<String, Double> findWallet(String email){
        try{

            var walletMap = new HashMap<String, Double>();
            var wallet = walletRepository.findByEmail(email);

            if(wallet.isEmpty()){
                throw new FinancialHttpException(FinancialExceptionCode.WALLET__NOT_FOUND_REPOSITORY_ERROR);
            }

            wallet.forEach(w -> {
                walletMap.put(w.getIsin(), w.getQuantity());
            } );

            return walletMap;

        } catch(DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.WALLET__FINDING_REPOSITORY_ERROR);
        }
    }

}
