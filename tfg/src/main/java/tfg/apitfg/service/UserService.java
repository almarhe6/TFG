package tfg.apitfg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tfg.apitfg.commons.BackendExceptionCode;
import tfg.apitfg.commons.BackendHttpException;
import tfg.apitfg.model.entity.User;
import tfg.apitfg.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ICacheService cacheService;

    @Override
    public void createUser(User user) {
        try {
            userRepository.save(user);
        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.USER__CREATE_REPOSITORY_ERROR);
        }
    }

    @Override
    @CacheEvict(value = "users", key = "#user.email")
    public void deleteUser(User user) {
        try {
            userRepository.deleteById(user.getEmail());
        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.USER__DELETE_REPOSITORY_ERROR);
        }
    }

    @Override
    @CacheEvict(value = "users", key = "#user.email")
    public void modifyUser(User user) {
        try {
            var oldUser = userRepository.findById(user.getEmail());

            if (oldUser.isEmpty()) {
                throw new BackendHttpException(BackendExceptionCode.USER__NOT_FOUND_REPOSITORY_ERROR);
            }
            var newUser = User.builder()
                    .email(user.getEmail())
                    .name(
                            user.getName() != null
                                    ? user.getName()
                                    : oldUser.get().getName())
                    .surname(
                            user.getSurname() != null
                                    ? user.getSurname()
                                    : oldUser.get().getSurname())
                    .phone(
                            user.getPhone() != null
                                    ? user.getPhone()
                                    : oldUser.get().getPhone())
                    .documentNumber(
                            user.getDocumentNumber() != null
                                    ? user.getDocumentNumber()
                                    : oldUser.get().getDocumentNumber())
                    .cardNumber(
                            user.getCardNumber() != null
                                    ? user.getCardNumber()
                                    : oldUser.get().getCardNumber())
                    .build();

            userRepository.save(newUser);

        } catch (DataAccessException e) {
            throw new BackendHttpException(BackendExceptionCode.USER__MODIFY_REPOSITORY_ERROR);
        }
    }

    @Override
    public User findUser(String email) {
        try {
            return cacheService.findUserFromCache(email);
        } catch (Exception e) {
            return cacheService.findUserFromDatabase(email);
        }
    }
}
