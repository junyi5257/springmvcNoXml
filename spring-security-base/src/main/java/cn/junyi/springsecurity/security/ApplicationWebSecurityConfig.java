package cn.junyi.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by GOUJY on 2017-12-29 13:53.
 *
 * @author goujy
 * @version 1.0
 *          <p>
 *          //@EnableWebSecurity  通用启用Security配置
 *          <p>
 *          //@EnableWebSecurity注解以及WebSecurityConfigurerAdapter一起配合提供基于web的security。
 *          <p>
 *          要求用户在进入你的应用的任何URL之前都进行验证
 *          创建一个用户名是“user”，密码是“password”，角色是“ROLE_USER”的用户
 *          启用HTTP Basic和基于表单的验证
 *          Spring Security将会自动生成一个登陆页面和登出成功页面
 */
@EnableWebSecurity(debug = true)
@Configuration
public class ApplicationWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * configure(HttpSecurity http) 方法
     * 通过 authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护。
     * 例如代码指定了 / 和 /home 不需要任何认证就可以访问，其他的路径都必须通过身份验证。
     *
     * @param http http
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .authorizeRequests()
                    .antMatchers("/","/base").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    /**
     * 设置忽略的请求
     * @param web WebSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web);
        web.ignoring().antMatchers("/css/**");
    }

    /**
     *
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    /**
     * //向内存中添加一个用户;
     * @param auth AuthenticationManagerBuilder
     * @throws Exception Exception
     */
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/

}
