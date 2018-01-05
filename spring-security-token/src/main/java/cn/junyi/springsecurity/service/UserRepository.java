package cn.junyi.springsecurity.service;

import cn.junyi.springsecurity.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by GOUJY on 2018-01-04 10:51.
 * User类的CRUD操作
 *
 * @author goujy
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {


    /*

    CrudRepository 接口继承于 Repository 接口，并新增了如下方法
    long count();
    boolean exists(Integer arg0);

    <S extends User> S save(S arg0);
    <S extends User> Iterable<S> save(Iterable<S> arg0);

    void delete(Integer arg0);
    void delete(Iterable<? extends User> arg0);
    void delete(User arg0);
    void deleteAll();

    StudentPO findOne(Integer arg0);
    Iterable<User> findAll();
    Iterable<User> findAll(Iterable<Integer> arg0);
    */

    @Query("")
    User findByUsername(String username);


}
