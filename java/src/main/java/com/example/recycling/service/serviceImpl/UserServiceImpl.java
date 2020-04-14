package com.example.recycling.service.serviceImpl;

import com.example.recycling.commons.core.R;

import com.example.recycling.dao.UserMapper;
import com.example.recycling.entity.User;
import com.example.recycling.service.UserService;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jyh
 * @version 1.0
 * @date 2020/1/11 16:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean isExist(User user) {
        User dataUser = userMapper.selectOne(user);
        if (dataUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public R isLogin(User user) {
        R result=R.ok();
        User user1=new User();
        user1.setUsername(user.getUsername());
        User dataUser = userMapper.selectOne(user1);//登录账号信息
        if(!user.getPwd().equals(dataUser.getPwd())){
            result.code(-1);//登录失败
            result.msg("密码错误");
            return result;
        }


        result.put("user",dataUser);
        result.msg("登录成功");
        System.out.println("返回用户认证信息");
        return result;
    }
}
