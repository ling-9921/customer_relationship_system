package com.ling.manager.employee.filter;

import com.ling.employee.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author ling
 * @time 2022/6/28 15:56
 */
@Component
public class LoginFilter implements Filter {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Employee employee = (Employee)redisTemplate.opsForValue().get("login");
        if (Objects.nonNull(employee)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/emp/login");
            System.err.println("没有登入");
        }
    }
}
