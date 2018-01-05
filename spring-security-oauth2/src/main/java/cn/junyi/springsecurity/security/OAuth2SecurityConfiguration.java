package cn.junyi.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by goujy on 2017/12/21.
 * 进行Spring Security配置
 * <p>
 * Authentication ----认证;
 * Basic Authentication
 *
 * @author goujy
 */
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 为Oauth2.0提供的Autowired 方法,ClientDetailsService
     */



    /**
     * 在内存中创建两个用户
     * <p>
     * 创建用户的方式有多种,
     *
     * @param auth auth
     * @throws Exception Exception
     */
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("bill").password("abc123").roles("admin1").and()
                .withUser("bob").password("abc123").roles("USER");
    }



    /**
     *
     第一种方式:
    在使用@Autowired 注入的形式,可以不用关注具体的方法名称
    只需要注意两点:
    I: annotate the method with @Autowired
    II:the method MUST be in a class annotated with one of the following :
        @EnableWebSecurity,
        @EnableWebMvcSecurity,
        @EnableGlobalMethodSecurity, or
        @EnableGlobalAuthentication

    //好处:这种形式可以不用在 extends WebSecurityConfigurerAdapter 的类中;

    @Autowired
    public void antMethodName(AuthenticationManagerBuilder auth) throws Exception {
         //DO SomeThing
    }

    第二种方式:
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
     }


    */

    /**
     * 设置允许请求的URL,不被权限拦截;
     * /oauth/token 请求是默认的请求,
     *
     * 在 @see org.springframework.security.oauth2.provider.endpoint.TokenEndPoint类中,
     *
     *
     *
     * @param http http
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }





}
