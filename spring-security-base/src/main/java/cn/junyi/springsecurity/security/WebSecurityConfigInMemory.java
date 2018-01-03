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
 *
 *          ☆☆☆☆☆将不使用的 WebSecurityConfig 的两个注解注释掉，即可☆☆☆☆☆
 */
//@EnableWebSecurity
//@Configuration
public class WebSecurityConfigInMemory extends WebSecurityConfigurerAdapter {


   /*
    II，适用于基于数据库表用户存储认证 配置user-detail服务
    @Resource
    DataSource dataSource;
    */


   /*
    III,适用第三种,自定义的UserDetailService,见WebSecurityConfigUserDetail.java
    @Autowired
    UserDao userDaoImpl;
    */

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
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/", "/base", "/base/*").permitAll()
                .antMatchers("/user/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //处理跳转403非授权页面
                .and().exceptionHandling().accessDeniedPage("/access?error");
        // @formatter:on
    }

    /**
     * 设置忽略的请求
     *
     * @param web WebSecurity
     * @throws Exception Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/index.html");
    }

    /**
     * configure(AuthenticationManagerBuilder) 通过重载，配置user-detail服务
     * I,基于内存的用户存储
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN", "USER");
    }

    /**
     * II,基于数据库表用户存储认证 配置user-detail服务
     *  auth.jdbcAuthentication().dataSource(dataSource)设置DataBase连接池;
     */
   /*
    * 测试通过;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于数据库的用户存储、认证,
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select name,password,true from user where name=?")
                .authoritiesByUsernameQuery("SELECT NAME,r.rolename FROM user u\n" +
                        "LEFT JOIN user_role ur ON u.id = ur.user_id\n" +
                        "LEFT JOIN role r ON ur.role_id = r.id\n" +
                        "where u.name =?");
    }*/

    /**
     * III,配置自定义的用户存储认证
     * 实现 UserDetailsService接口的 loadUserByUsername方法，并返回 接口对象 UserDetails
     * XXXXXXXUserDetailsService implements UserDetailsService{
     *     public UserDetails loadUserByUsername(String username){
     *         return null;
     *     }
     * }
     */
    /*
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            //基于数据库的用户存储、认证,
            auth.userDetailsService(userDaoImpl);
        }
    */


    /**
     * I.0//向内存中添加一个用户;
     * 持有AuthenticationManagerBuilder 对象的另外一种操作方式;
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
