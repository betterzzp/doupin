package com.zhouzhongping.cn.mapper;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhouzhongping.cn.entity.Users;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
    	BCrypt bCrypt = new BCrypt();
    	ArrayBlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
    	for(int i=0;i<7;i++) {
    		Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<1000000;i++) {
						Users user = new Users();
				        String salt = bCrypt.gensalt();
				        user.setId(UUID.randomUUID().toString().replace("-",""));
				        user.setEmail(null);
				        String passInfo =  UUID.randomUUID().toString().replace("-","");
				        user.setPassword(passInfo);
				        //设置手机号
				        user.setPhone(passInfo);
				        user.setUsername(passInfo);
				        user.setPortrait("http://zhouzhongping.cn/boduoyejieye.jpg");
				        user.setSalt(salt);
				        user.setPassword(bCrypt.hashpw(user.getPassword(), salt));
				        userMapper.insert(user);
					}
				}
    		});
    		thread.start();
    	}
    	try {
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
