package cn.junyi.springsecurity.service;

import cn.junyi.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 * Created by GOUJY on 2018-01-04 10:51.
 * User类的CRUD操作
 *
 * @author goujy
 * @version 1.0
 */
@Service
public class UserRepositoryImpl {

   /* @Autowired
    private UserRepository userRepository;*/

    User findByUsername(String username) {
        return null;
    }


}
