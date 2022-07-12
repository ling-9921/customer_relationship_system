package com.ling.manager.configuration;

import com.ling.manager.employee.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author ling
 * @time 2022/6/28 16:05
 */
@Configuration
public class WebFilter {

    @Autowired
    LoginFilter loginFilter;

    @Bean
    public FilterRegistrationBean<LoginFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean = new FilterRegistrationBean<>();
        loginFilterRegistrationBean.setFilter(loginFilter);
        loginFilterRegistrationBean.addUrlPatterns("/emp/*");
        loginFilterRegistrationBean.setOrder(1);
        return loginFilterRegistrationBean;
    }
}
