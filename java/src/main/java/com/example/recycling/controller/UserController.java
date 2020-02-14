package com.example.recycling.controller;

import com.example.recycling.commons.core.R;
import com.example.recycling.entity.User;
import com.example.recycling.service.UserService;
import com.example.recycling.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jyh
 * @version 1.0
 * @date 2020/1/11 16:48
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("isLogin")
    public R isUserLogin(@RequestBody User user) {
        R result = R.ok();
        result = userService.isLogin(user);
        return result;
    }


}
