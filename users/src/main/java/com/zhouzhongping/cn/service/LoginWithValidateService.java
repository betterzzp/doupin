package com.zhouzhongping.cn.service;

import cn.zhouzhongping.CommonResponse;
import com.zhouzhongping.cn.entity.Users;
import com.zhouzhongping.cn.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import redis.clients.jedis.Jedis;

public class LoginWithValidateService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String loginWithValidateCode(String username,String validateCode){
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Users user = userService.selectByName(username);
            CommonResponse cr = new CommonResponse();
            if(user!=null){
            }else{
                Jedis jedis = new Jedis("localhost", 6379);
                String code = jedis.get(username);
                if(code.equals(validateCode) ){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    token = jwtTokenUtil.generateToken(userDetails);
                }

            }

        } catch (AuthenticationException e) {
        }
        return token;
    }
}
