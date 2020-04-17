package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-boot-03-logging
 * @ClassName: DruidConfig
 * @Description: druid数据监控
 * @Author: 842712494@qq.com
 * @Date: 2020/4/17 11:27
 * @Version: 1.0.0
 */

@Configuration
public class DruidConfig {

    /**
     * @Description: 手动配置数据源，添加额外参数
     * @MethodName: druid
     * @Param: []
     * @Return: javax.sql.DataSource
     * @Author: 842712494@qq.com
     * @Date: 2020/4/17 11:29
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> config = new HashMap<>();
        config.put(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        config.put(StatViewServlet.PARAM_NAME_PASSWORD, "123456");
        config.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        bean.setInitParameters(config);
        return bean;
    }

    @Bean
    public FilterRegistrationBean aaa() {

        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());

        Map<String, String> config = new HashMap<>();
        config.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
        bean.setInitParameters(config);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
