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
 *
 * @author goujy
 */
@Configuration
@ComponentScan(basePackages={"cn.junyi.springsecurity"},excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class RootConfig {


    /**
     * 注意这里的数据源注入问题,不要放到SpringMVC的类下面.
     * @return
     */

    @Bean(destroyMethod="close")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://59.110.217.87:3306/springbootdb");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("YabtOwF6M1mr");
        return druidDataSource;
    }


    /**
     * SpringJDBC Template
     */
   /* @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }*/
}