package cn.junyi.springsecurity.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.provider.MD5;

/**
 * Created by GOUJY on 2018-01-03 14:59.
 *
 * @author goujy
 * @version 1.0
 */
public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {
        String pass = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("admin",hashPass);
        System.out.println(f);

        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

        String md5Str = md5PasswordEncoder.encode(pass);
        System.out.println(md5Str);
        System.out.println(md5PasswordEncoder.matches(pass,md5Str));

    }
}
