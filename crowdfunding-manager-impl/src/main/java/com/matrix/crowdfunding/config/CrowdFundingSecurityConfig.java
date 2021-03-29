package com.matrix.crowdfunding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 配置类
 *
 * @Author: yihaosun
 * @Date: 2021/3/29 22:34
 */
@Configuration
@EnableWebSecurity // web 注解
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 细粒度的注解, 别忘了配置为true
public class CrowdFundingSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置放行的路径, 剩下都需要认证
        http.authorizeRequests().antMatchers("/static/**", "/welcome.jsp","/toLogin")
                .permitAll()
                .anyRequest()
                .authenticated();
        // /login.jsp==POST 用户登陆请求发给Security  loginPage("/toLogin") 里面的参数是DispatcherController的登陆映射
        http.formLogin().loginPage("/toLogin")
                .usernameParameter("loginacct")
                .passwordParameter("userpswd")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/main");
        // 禁止跨域
        http.csrf().disable();
        // 注销之后的地址映射(在DispatcherController中)
        http.logout().logoutSuccessUrl("/index");
        // 异常处理
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                               AccessDeniedException accessDeniedException) throws IOException, ServletException {
                //   X-Requested-With: XMLHttpRequest
                String type = request.getHeader("X-Requested-With");
                if("XMLHttpRequest".equals(type)) {
                    response.getWriter().print("403"); // 403 权限不够，访问被拒绝
                }else {
                    request.getRequestDispatcher("/WEB-INF/jsp/error/error403.jsp").forward(request, response);
                }
            }
        });
        // 记住我功能
        http.rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
