package com.example.recycling.service;

import com.example.recycling.commons.core.R;
import com.example.recycling.entity.User;

/**
 * @author jyh
 * @version 1.0
 * @date 2020/1/11 16:21
 */
public interface UserService {

    Boolean isExist(User user);//判断是否用户已存在

    R isLogin(User user);//判断用户登录
}
