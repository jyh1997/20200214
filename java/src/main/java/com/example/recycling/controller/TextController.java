package com.example.recycling.controller;

import com.example.recycling.commons.core.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author jyh
 * @version 1.0
 * @date 2020/1/11 17:12
 */
@RestController
@RequestMapping
public class TextController {

    @GetMapping("text")
    private R text() {
        R result = R.newInstance();
        result.msg("测试连接成功");
        System.out.println("连接测试：连接成功");
        return result;
    }
}
