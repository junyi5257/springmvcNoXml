package cn.junyi.springsecurity.service;

import cn.junyi.springsecurity.model.User;

import java.util.List;

/**
 * Created by goujy on 2017/12/21.
 *
 * @author goujy
 */
public interface UserService {
    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(User user);
}
