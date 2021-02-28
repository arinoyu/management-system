package com.arino.security;

import com.arino.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String msg = "请求失败";
        if (e instanceof LockedException) {
            msg = "账户被锁定，请联系管理员!";
        } else if (e instanceof CredentialsExpiredException) {
            msg = "密码过期，请联系管理员!";
        } else if (e instanceof AccountExpiredException) {
            msg = "账户过期，请联系管理员!";
        } else if (e instanceof DisabledException) {
            msg = "账户被禁用，请联系管理员!";
        } else if (e instanceof UsernameNotFoundException) {
            msg = "用户名不存在！";
        } else if (e instanceof BadCredentialsException) {
            msg = "密码错误！";
        }
        Result result = Result.fail(msg, 400);
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
