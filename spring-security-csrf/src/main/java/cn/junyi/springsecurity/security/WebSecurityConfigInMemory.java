package cn.junyi.springsecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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
 *          <p>
 *          配置不同的数据源，包括内存中,JDBC和自定义的UserServiceImpl
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfigInMemory extends WebSecurityConfigurerAdapter {


    /**
     * 在内存中设置三个用户
     *
     * @param auth auth
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("dba").roles("DBA");
    }

    /**
     * 配置权限要求
     * 采用注解方式，默认开启csrf,
     * // csrf校验,默认是排除 Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS")); 这几类操作的。
     *
     *
     * <p>
     * 如果在AJAX中使用了Csrf--
     * <meta name="_csrf" content="${_csrf.token}"/>
     * <meta name="_csrf_header" content="${_csrf.headerName}"/>
     *
     * var token = $("meta[name='_csrf']").attr("content");
     * var header = $("meta[name='_csrf_header']").attr("content");
     *
     * $(document).ajaxSend(function(e, xhr, options) {
     *      xhr.setRequestHeader(header, token);
     * });
     *
     * @param http http
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //.access("hasRole('ROLE_ADMIN')")  //两种方式都可以
                .antMatchers("/dba/**").hasAnyRole("ADMIN", "DBA")
                //.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/welcome").failureUrl("/login?error")
                //这里没有使用默认的用户名和密码;
                .usernameParameter("user-name").passwordParameter("pwd")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                // csrf校验,默认是排除 Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS")); 这几类操作的。
                .csrf()
                .and()
                .exceptionHandling().accessDeniedPage("/access?denied");
        //注意，这里启用csrf功能;
        //<input type="hiden" name="${_csrf.parameterName}" value="${_csrf.token}"}
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web);
        //do nothing
    }
}
