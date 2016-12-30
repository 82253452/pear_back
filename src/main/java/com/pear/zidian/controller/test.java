package com.pear.zidian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/12/24.
 */
@Controller
@RequestMapping
public class test {
    @RequestMapping("/tt")
    @ResponseBody
    public String  tt(){
        System.out.println("是否");
        return "a是饭店";
    }
}
