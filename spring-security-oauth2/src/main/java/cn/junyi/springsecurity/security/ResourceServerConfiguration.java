package cn.junyi.springsecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * Created by goujy on 2017/12/21.
 * * 资源服务器,哪些资源(请求) 访问受限;
 * //@EnableResourceServer 特指用于Oauth2.0认证,
 * 自动增加了一个类型为 OAuth2AuthenticationProcessingFilter 的过滤器链，
 *
 *
 *  资源服务器承载资源[REST API]，客户端感兴趣的资源位于  /user/ 。
 * //  @EnableResourceServer注释，适用在OAuth2资源服务器，
 * 实现了Spring Security的过滤器验证的请求传入OAuth2令牌。
 * ResourceServerConfigurerAdapter类实现 ResourceServerConfigurer 提供的方法
 * 来调整 OAuth2安全保护的访问规则和路径。
 *
 *
 *
 *
 * @author goujy
 *
 *
 *
 *
 *  //@EnableResourceServer --->注解中有@Import(ResourceServerConfiguration.class)类，
 *  //  ResourceServerConfiguration类，extends WebSecurityConfigurerAdapter ，和普通的配置WebSecurity类似
 *
 *  //  ResourceServerConfiguration类， 在复写 void configure(HttpSecurity http)时【方法控制谁进谁要授权】，
 *
 *  //  创建了 ResourceServerSecurityConfigurer对象,
 *
 *      这个对象的方法中，有创建 OAuth2AuthenticationProcessingFilter
 *      resourcesServerFilter = new OAuth2AuthenticationProcessingFilter();
 *
 *      resourcesServerFilter.setAuthenticationEntryPoint(authenticationEntryPoint);
 *      resourcesServerFilter.setAuthenticationManager(oauthAuthenticationManager);
 *      和管理资源的 configure(HttpSecurity http) 方法;
 *
 *  //
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    //将类型命名,可以有多个类似的控制;
    private static final String RESOURCE_ID = "my_rest_api";

    /**
     * 这里将资源管理起来;
     *
     *
     * @param resources ResourceServerSecurityConfigurer
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .requestMatchers().antMatchers("/user*/**")
                .and().authorizeRequests()
                .antMatchers("/user*/**").permitAll()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
                //.and().exceptionHandling().accessDeniedPage("/base");
    }
}
