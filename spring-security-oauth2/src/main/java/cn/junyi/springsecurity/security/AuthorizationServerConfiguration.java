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
 * 认证授权服务器配置
 * Authorization ----授权
 * <p>
 * eg：https://github.com/spring-projects/spring-security-oauth/tree/master/samples/oauth2
 * <p>
 * <p>
 * <p>
 * Authentication ----认证;
 * Basic Authentication
 * <p>
 * https://www.cnblogs.com/xingxueliao/p/5911292.html
 * Created by goujy on 2017/12/21.
 * //@EnableAuthorizationServer 注解来配置OAuth2.0 授权服务机制
 * <p>
 * Spring OAuth2.0提供者实际上分为：
 * 授权服务 AuthorizationService-------AuthorizationServerConfigurerAdapter.
 * 资源服务 ResourceService -------ResourceServerConfigurerAdapter.
 *
 * @author goujy
 *         <p>
 *         //@EnableAuthorizationServer 注解来配置OAuth2.0 授权服务机制,特指OAuth2.0授权机制;
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
     * <p>
     * 这里可以自己定义一个ClientDetailService 服务;
     * clients.withClientDetails(clientDetailsService);
     *
     * @param clients clients
     * @throws Exception Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //可以设置多个客户端,这里是单一一个客户端;

        clients.inMemory()
                //客户端ID,会被Base64编码; 必填;
                .withClient("my-trusted-client")

                //设置支持的类型,这里设置为四种都支持 [此客户端可以使用的授权类型，默认为空。]
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")

                //授权用户的操作权限 [用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。]
                .scopes("read", "write", "trust")

                //客户端安全码,[使用时,客户端ID:客户端安全码 一起被Base64编码位Authorization ]
                //在代码中即:Base64.encodeBase64("my-trusted-client:secret".getBytes())
                .secret("secret")

                //token有效期为120秒
                .accessTokenValiditySeconds(6000);


    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * 配置允许的哪些认证;
     *
     * @param oauthServer oauthServer
     * @throws Exception Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        // oauthServer.realm(REALM + "/client").allowFormAuthenticationForClients();
        // 允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }

    /**
     * 用来配置授权（authorizationManager）以及令牌（token）的访问端点
     * <p>
     * 和令牌服务(token services)。
     *
     * @param endpoints endpoints
     * @throws Exception Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)//.userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
    }


    /**
     * 使用密码(grant_type:password)获取Token的方式,
     *  java代码模式：SpringRestClient
     *
     *  浏览器模拟模式:设置Headers
     *          Key：Content-Type   Value:application/x-www-form-urlencoded
     *          Key: Authorization  Value:Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0
     *
     *         设置Body
     *
     *         Key:grant_type   Value:password
     *         Key:username     Value:bob
     *         Key:password     Value:abc123
     *
     *  Ajax请求方式:
     *  Oauth2
     *
     *  function requestOauthToken(username, password) {
     var success = false;
     $.ajax({
     url: 'oauth/token',
     datatype: 'json',
     type: 'post',
     headers: {'Authorization': 'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0'},
     contentType: 'application/x-www-form-urlencoded',
     async: false,
     data: {
     username: username,
     password: password,
     grant_type: 'password'
     },
     success: function (data) {
     localStorage.setItem('token', data.access_token);
     success = true;
     },
     error: function () {
     removeOauthTokenFromStorage();
     }
     });

     return success;
     }

     function getOauthTokenFromStorage() {
     return localStorage.getItem('token');
     }

     function removeOauthTokenFromStorage() {
     return localStorage.removeItem('token');
     }
     */

}
