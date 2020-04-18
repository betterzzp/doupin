package com.zhouzhongping.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzhongping.cn.entity.Users;
import com.zhouzhongping.cn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Component
@Repository
public class userServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void inset(Users user ){
        userMapper.insert(user);
    }
    @Override
    public Users select() {
        Users user = new Users();
        return null;
    }
    @Override
    public Users selectByName(String username) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Users::getUsername,username);
        List<Users> userList = userMapper.selectList(queryWrapper);
        if(userList.size()==0){
            return null;
        }else {
            return userList.get(0);
        }
    }
}