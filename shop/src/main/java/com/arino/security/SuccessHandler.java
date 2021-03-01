package com.arino.security;

import com.arino.common.Result;
import com.arino.pojo.User;
import com.arino.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 获取登录用户
        User user= (User) authentication.getPrincipal();

        // 生成一个list存储roles
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }

        // 利用用户名、roles生成token
        String token = JWTUtil.createToken(user.getUsername(), roles, JWTUtil.DEFAULT_SECRET, true);
        token = JWTUtil.TOKEN_PREFIX + token;

        Map<Object,Object> map=new HashMap<>();
        map.put("id",user.getId());
        map.put("rid",user.getRid());
        map.put("username",user.getUsername());
        map.put("mobile",user.getMobile());
        map.put("email",user.getEmail());
        map.put("token",token);
        Result result = Result.success("登录成功", map);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setHeader(JWTUtil.TOKEN_HEADER, token);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
