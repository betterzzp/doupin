package com.zhouzhongping.cn.service;

import com.zhouzhongping.cn.util.JwtTokenUtil;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class AdminService {
        @Autowired
        private JwtTokenUtil jwtTokenUtil;
        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private StringRedisTemplate redisTemplate;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Value("${jwt.tokenHead}")
        private String tokenHead;
        public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            //使用Redis重构
         
            //UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //SecurityContextHolder.getContext().setAuthentication(authentication);
           
            token = jwtTokenUtil.generateToken(userDetails);
            //long expire =1000*60*60*48;
			//redisTemplate.opsForValue().set(String.format(tokenHead,token),username,expire , TimeUnit.SECONDS);
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("用户名错误");
        }
        return tokenHead+token;
    }
        
        public String loginWithValidateCode(String username) {
            String token = null;
            //密码需要客户端加密后传递
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                token = jwtTokenUtil.generateToken(userDetails);
            } catch (AuthenticationException e) {
                throw new UsernameNotFoundException("用户名错误");
            }
            return tokenHead+token;
        }
}
