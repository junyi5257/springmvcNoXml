package cn.junyi.springsecurity.security;

import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by GOUJY on 2018-01-09 11:30.
 * <p>
 * 不使用系统的ClientDetailService,可以使用自己定义的;
 *
 * @author goujy
 * @version 1.0
 */
//@Service
public class MyClientDetailsServiceImpl implements ClientDetailsService {
    private ClientDetailsService clientDetailsService;

    @PostConstruct
    public void init() {
        InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder = new InMemoryClientDetailsServiceBuilder();
        // @formatter:off
        inMemoryClientDetailsServiceBuilder
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
        // @formatter:on
        try {
            clientDetailsService = inMemoryClientDetailsServiceBuilder.build();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println("loadClientByClientId*********************************:" + clientId);
        return clientDetailsService.loadClientByClientId(clientId);
    }
}

