package cn.junyi.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by GOUJY on 2018-01-04 10:52.
 *
 * @author goujy
 * @version 1.0
 */
@Entity
@Table(name = "user_")
public class User {

    //用户名
    @Column(name = "username_")
    private String username;

    //密码
    @Column(name = "password_")
    private String password;

    //用户id
    @Id
    @Column(name = "id_")
    private long id;

    //昵称
    @Column(name = "nickname_")
    private String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
