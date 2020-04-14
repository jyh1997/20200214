package com.example.recycling.controller;

import com.example.recycling.commons.core.R;
import com.example.recycling.commons.utils.BeanMapUtils;
import com.example.recycling.entity.User;
import com.example.recycling.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public R isUserLogin(@RequestBody Map<String, Object> map) {
        R result = R.ok();
        User user = null;
        try {
            user = BeanMapUtils.mapToBean(map, User.class);
        } catch (Exception e) {
            result.code(22);
            result.msg("接受数据失败");
        }
        result = userService.isLogin(user);
        return result;
    }


}
