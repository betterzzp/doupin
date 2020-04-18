package com.zhouzhongping.cn.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzhongping.cn.entity.UmsAdminLoginParam;
import com.zhouzhongping.cn.entity.Users;
import com.zhouzhongping.cn.mapper.UserMapper;
import com.zhouzhongping.cn.service.AdminService;
import com.zhouzhongping.cn.service.SmsService;
import com.zhouzhongping.cn.service.UserService;
import com.zhouzhongping.cn.util.ZhouUtil;

import cn.zhouzhongping.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("user")
@Slf4j
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SmsService smsService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(value = "registory",method = {RequestMethod.POST})
    @SuppressWarnings("all")
    public CommonResponse Login(@RequestBody Users users){
    	CommonResponse cr = new CommonResponse();
    	BCrypt bCrypt = new BCrypt();
    	String salt = bCrypt.gensalt();
    	users.setId(UUID.randomUUID().toString().replace("-",""));
    	users.setPassword(bCrypt.hashpw(users.getPassword(), salt));
    	users.setPhone(users.getPhone());
    	users.setPortrait("http://zhouzhongping.cn/boduoyejieye.jpg");
    	users.setSalt(salt);
    	
    	synchronized(this) {
    		QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        	queryWrapper.eq("phone", users.getPhone());
        	List<Users> userList = userMapper.selectList(queryWrapper);
    		
    		if(userList.size()>0) {
    			cr.setCode("10002");
    	        cr.setMessage("手机号已经注册");
    	        cr.setSuccess(true);
    	        cr.setContent(null);
    	        return cr;
    		}
    		
    		QueryWrapper<Users> queryWrapperUsername = new QueryWrapper<Users>();
    		queryWrapperUsername.eq("username", users.getUsername());
        	List<Users> userListUsername = userMapper.selectList(queryWrapperUsername);
        	
        	if(userListUsername.size()>0) {
    			cr.setCode("10003");
    	        cr.setMessage("用户名已经被占用");
    	        cr.setSuccess(true);
    	        cr.setContent(null);
    	        return cr;
    		}
    	}
    	
        userMapper.insert(users);
        cr.setCode("10001");
        cr.setMessage("登录成功");
        cr.setSuccess(true);
        cr.setContent("登录成功");
        return cr;
    }
    @SuppressWarnings("all")
    @RequestMapping( value = "/sendMsg/{phoneNumber}", method = RequestMethod.POST)
    public CommonResponse sendMsg(@PathVariable String phoneNumber){
        CommonResponse cr = new CommonResponse();
        String validateCode = ZhouUtil.getValiteCode();
        boolean sendResult = smsService.sendMsg(phoneNumber,validateCode);
        if(sendResult){
            Jedis jedis = new Jedis("localhost", 6379);
            jedis.set(phoneNumber, validateCode);
            jedis.expire(phoneNumber,600);
        }
        cr.setCode("10001");
        cr.setMessage("登录成功");
        cr.setSuccess(true);
        cr.setContent(null);
        return cr;
    }
    @SuppressWarnings("all")
    @RequestMapping( value = "/login/loginwithvalidatecode", method = RequestMethod.POST)
    public CommonResponse loginWithUsernameAndPassword(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        //手机登录方案
        CommonResponse cr = new CommonResponse();
        try{
            String username = umsAdminLoginParam.getPhoneNumber();
            String validateCode = umsAdminLoginParam.getValidatecode();
            Jedis jedis = new Jedis("localhost", 6379);
            String redisValidateCode = jedis.get(username);
            if(redisValidateCode == null || redisValidateCode!=null&&"".equals(redisValidateCode)) {
            	cr.setCode("204");
                cr.setMessage("验证码已经过期");
                cr.setSuccess(false);
                cr.setContent(null);
                return cr;
            }else {
            	if(validateCode.equals(redisValidateCode)) {
            		QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
            		queryWrapper.eq("username", username);
            		List<Users>  userList = userMapper.selectList(queryWrapper );
            		if(userList.size()>0) {
            			 String token = adminService.loginWithValidateCode(username);
                         if(token != null){
                             cr.setCode("200");
                             cr.setMessage("登录成功");
                             cr.setSuccess(true);
                             cr.setContent(token);
                         }else{
                             cr.setCode("201");
                             cr.setMessage("请完成注册信息");
                             cr.setSuccess(false);
                             cr.setContent(null);
                         }
            		}else {
            			 cr.setCode("210");
                         cr.setMessage("尚未注册，请跳转登录页面先注册");
                         cr.setSuccess(true);
                         cr.setContent(null);
            		}
            	}else {
            		cr.setCode("205");
                    cr.setMessage("验证码错误");
                    cr.setSuccess(false);
                    cr.setContent(null);
                    return cr;
            	}
            }
        }catch (BadCredentialsException e){
            cr.setCode("202");
            cr.setMessage("用户名或者密码错误");
            cr.setSuccess(false);
        }catch (UsernameNotFoundException e){
            cr.setCode("203");
            cr.setMessage("用户名或者密码错误");
            cr.setSuccess(false);
        }
        return cr;
    }
    @SuppressWarnings("all")
    @RequestMapping( value = "/login/loginwithPassword", method = RequestMethod.POST)
    public CommonResponse loginWithPhoneNumber(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
    	long start = System.currentTimeMillis();
        String username = umsAdminLoginParam.getPhoneNumber();
        String password = umsAdminLoginParam.getPassword();
        String token = adminService.login(username, password);
        CommonResponse cr = new CommonResponse();
        if(token!=null) {
            cr.setCode("200");
            cr.setMessage("登录成功");
            cr.setSuccess(true);
            cr.setContent(token);
        }else{
            cr.setCode("500");
            cr.setMessage("账户或密码错误");
            cr.setSuccess(true);
            cr.setContent(token);
        }
        long end = System.currentTimeMillis();
        log.info("本次登录消耗时间为"+(end-start));
        return cr;
    }
    @SuppressWarnings("all")
    @RequestMapping(value = "springmvc",method = {RequestMethod.POST})
    public String springmvc(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        return null;
    }
}
