package com.pear;

import com.pear.mapper.TestMapper;
import com.pear.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Created by admin on 2016/12/21.
 */
@Controller
@SpringBootApplication
public class main {
    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/")
    @ResponseBody
    public void test(){
//        List<Test> list =testMapper.selectAll();
        System.out.println("11111111111111111111111111111");
//        System.out.println(list.get(0).getTest());
    }
    public static void main(String[] args) {
        SpringApplication.run(main.class);
    }
}
