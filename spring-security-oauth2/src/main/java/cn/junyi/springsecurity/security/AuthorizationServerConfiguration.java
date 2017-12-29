package cn.junyi.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
/**
 * 认证服务器配置
 * Authorization ----授权
 *
 *
 *
 * Authentication ----认证;
 * Basic Authentication
 *
 *  https://www.cnblogs.com/xingxueliao/p/5911292.html
 * Created by goujy on 2017/12/21.
 * //@EnableAuthorizationServer 注解来配置OAuth2.0 授权服务机制
 *
 * @author goujy
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final String REALM = "MY_OAUTH_REALM";

    @Autowired
    private TokenStore tokenStore;

   // @Autowired
   // private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients clients
     * @throws Exception Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                //客户端ID,会被Base64编码; 必填;
                .withClient("my-trusted-client")
                //设置支持的类型,这里设置为四种都支持 [此客户端可以使用的授权类型，默认为空。]
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                //授权用户的操作权限 [用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。]
                .scopes("read", "write", "trust")
                //客户端安全码,[使用时,客户端ID:客户端安全码 一起被Base64编码位Authorization ]
                //Base64.encodeBase64("my-trusted-client:secret".getBytes())
                .secret("secret")
                //token有效期为120秒
                .accessTokenValiditySeconds(6000);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * @param oauthServer oauthServer
     * @throws Exception Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
       // oauthServer.realm(REALM + "/client");
    }

    /**
     * 用来配置授权（authorizationManager）以及令牌（token）的访问端点
     *
     * 和令牌服务(token services)。
     * @param endpoints endpoints
     * @throws Exception Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)//.userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
    }


}
