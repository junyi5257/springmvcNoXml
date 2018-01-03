package cn.junyi.springsecurity;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by goujy on 2017/12/27.
 * 相当于spring-application.xml
 *
 * @author goujy
 */
@Configuration
@ComponentScan(basePackages = {"cn.junyi.springsecurity"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {


    /**
     * 非数据库模式,可以不使用这样的连接方式;
     * <p>
     * 注意这里的数据源注入问题,不要放到SpringMVC的类下面.
     * 这里是用的是Druid数据库连接池,可以配置拦截器查看SQL运行效果;
     *
     * @return DataSource
     */

}