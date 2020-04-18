package com.zhouzhongping.cn.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;
import cn.zhouzhongping.CommonResponse;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CommonResponse cr = new CommonResponse();
        cr.setCode("403");
        cr.setMessage("你没有权限");
        cr.setSuccess(true);
        cr.setContent(true);
        response.getWriter().println(JSONUtil.parse(cr));
        response.getWriter().flush();
    }
}
