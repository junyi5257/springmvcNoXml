package cn.junyi.springsecurity;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by goujy on 2017/12/21.
 * SpringMVC DispatcherServlet的java版本;
 * @author goujy
 */
public class SpringInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //getRootConfigClasses()返回的配置类定义了Spring应用根容器中的beans。
    @Override
    protected Class<?>[] getRootConfigClasses() { //获取Spring应用容器的配置文件,包含dataSource配置等;
        return new Class[]{RootConfig.class};
    }


    //getServletConfigClasses()返回的配置类定义了Spring MVC应用容器中的beans；
    @Override
    protected Class<?>[] getServletConfigClasses() { //获取Spring MVC应用容器
        return new Class[]{SpringMvcConfig.class};
    }

    //指定需要由DispatcherServlet映射的路径，
    // 这里给定的是"/"，意思是由DispatcherServlet处理所有向该应用发起的请求。
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //Filter
    @Override
    protected Filter[] getServletFilters() {//添加拦截器
        Filter[] singleton = {new CORSFilter()};
        return singleton;
    }
}
