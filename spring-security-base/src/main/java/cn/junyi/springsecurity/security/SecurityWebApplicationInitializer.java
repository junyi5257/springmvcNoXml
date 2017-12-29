package cn.junyi.springsecurity.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by GOUJY on 2017-12-29 17:21.
 * <p>
 * <p>
 * //If you are not using Spring or Spring MVC,
 * // you will need to pass in the ApplicationWebSecurityConfig into the superclass
 * // to ensure the configuration is picked up. You can find an example below:
 * 加载 springSecurityFilterChain 如果不是spring 或者springmvc项目,需要手动配置
 * <code>ApplicationWebSecurityConfig<code/>类的路径;
 *
 * @author goujy
 * @version 1.0
 */


public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
   /*这里可以省略,但是类SecurityWebApplicationInitializer 不能省略;
    public SecurityWebApplicationInitializer() {
        super(ApplicationWebSecurityConfig.class);
    }*/
}
