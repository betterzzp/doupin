package com.zhouzhongping.cn.component;

import cn.zhouzhongping.CommonResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
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
