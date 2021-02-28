package com.arino.config;

import com.arino.common.Result;
import com.arino.filter.CustomAuthenticationFilter;
import com.arino.filter.JWTAuthorizationFilter;
import com.arino.filter.SimpleCORSFilter;
import com.arino.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final AuthenticationSuccessHandler successHandler;

    private final AuthenticationFailureHandler failureHandler;

    public SecurityConfig(UserService userService, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        this.userService = userService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，功能页只有对应权限的人才能访问
        // 请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/hello/**").hasRole("超级管理员")
                .antMatchers("/special/**").hasRole("arino");
        // 没有权限默认会到登录页面
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
        // 在Username过滤器前添加一个自定义过滤器处理application/json请求
        CustomAuthenticationFilter customFilter = new CustomAuthenticationFilter();
        customFilter.setAuthenticationManager(authenticationManagerBean());
        // 认证成功后的返回信息
        customFilter.setAuthenticationSuccessHandler(successHandler);
        // 认证失败后的返回信息
        customFilter.setAuthenticationFailureHandler(failureHandler);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        // 关闭csrf防御
        http.csrf()
                .disable()
                .exceptionHandling()
                // 未登录时的返回信息
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            Result result = Result.fail("尚未登录，请先登录", 400);
                            out.write(new ObjectMapper().writeValueAsString(result));
                            out.flush();
                            out.close();
                        }
                );
        // 设置session为无状态
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // token验证过滤器
        http.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加跨域过滤器
        http.addFilterBefore(new SimpleCORSFilter(), CustomAuthenticationFilter.class);
        //开启记住我功能
        http.rememberMe();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
