package cn.springboot.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Created by GOUJY on 2018-01-09 14:18.
 *
 * @author goujy
 * @version 1.0
 */
@SpringBootApplication
public class SpringSecurityOauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2Application.class, args);
    }

}
