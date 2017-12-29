package cn.junyi.springsecurity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by goujy on 2017/12/27.
 *
 * @author goujy
 */
@Configuration
@ComponentScan(basePackages={"cn.junyi.springsecurity"},excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class RootConfig {

}