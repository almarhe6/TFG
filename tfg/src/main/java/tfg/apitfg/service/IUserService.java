package tfg.apitfg.service;

import tfg.apitfg.model.entity.User;

public interface IUserService {
    void createUser(User user);
    void deleteUser(User user);
    void modifyUser(User user);
    User findUser(String email);
}
