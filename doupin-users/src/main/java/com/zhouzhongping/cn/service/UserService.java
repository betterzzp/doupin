package com.zhouzhongping.cn.service;
import com.zhouzhongping.cn.entity.Users;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@Component
public interface UserService {
    public void inset(Users user);
    public Users select();
    public Users selectByName(String username);
}
