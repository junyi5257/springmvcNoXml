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
 * @author goujy
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "my_rest_api";

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
