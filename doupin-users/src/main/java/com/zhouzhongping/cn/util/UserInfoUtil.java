package com.zhouzhongping.cn.util;

import com.zhouzhongping.cn.entity.AdminUserDetails;
import com.zhouzhongping.cn.entity.Users;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoUtil {
    public static Users getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //有登陆用户就返回登录用户，没有就返回null
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                AdminUserDetails adminUserDetails  = (AdminUserDetails) authentication.getPrincipal();
                return adminUserDetails.getUser();
            }
        }
        return null;
    }
}
