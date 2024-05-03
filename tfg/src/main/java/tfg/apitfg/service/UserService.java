package tfg.apitfg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import tfg.apitfg.commons.FinancialExceptionCode;
import tfg.apitfg.commons.FinancialHttpException;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{
    private UserRepository userRepository;

    @Override
    public void createUser(User user){
        try {
            userRepository.save(user);
        } catch(DataAccessException e){
            throw new FinancialHttpException(FinancialExceptionCode.USER__CREATE_REPOSITORY_ERROR);
        }
    }

    @Override
    public void deleteUser(User user){
        try{
            userRepository.deleteById(user.getEmail());
        } catch(DataAccessException e){
            throw new FinancialHttpException(FinancialExceptionCode.USER__DELETE_REPOSITORY_ERROR);
        }
    }

    @Override
    public void modifyUser(User user){
        try {
            var oldUser = userRepository.findById(user.getEmail());

            if (oldUser.isEmpty()) {
                throw new FinancialHttpException(FinancialExceptionCode.USER__NOT_FOUND_REPOSITORY_ERROR);
            }
                var newUser = User.builder()
                        .email(user.getEmail())
                        .name(user.getName() != null ? user.getName() : oldUser.get().getName())
                        .surname(user.getSurname() != null ? user.getSurname() : oldUser.get().getSurname())
                        .phone(user.getPhone() != null ? user.getPhone() : oldUser.get().getPhone())
                        .documentNumber(user.getDocumentNumber() != null ? user.getDocumentNumber() : oldUser.get().getDocumentNumber())
                        .cardNumber(user.getCardNumber() != null ? user.getCardNumber() : oldUser.get().getCardNumber())
                        .build();

                userRepository.save(newUser);

        } catch(DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.USER__MODIFY_REPOSITORY_ERROR);
        }
    }

    @Override
    public User retrieveUser(String email){
        try {
            var user = userRepository.findById(email);

            if (user.isEmpty()){
                throw new FinancialHttpException(FinancialExceptionCode.USER__NOT_FOUND_REPOSITORY_ERROR);
            }

            return user.get();

        } catch(DataAccessException e) {
            throw new FinancialHttpException(FinancialExceptionCode.USER__RETRIEVE_REPOSITORY_ERROR);
        }
    }
}
