package com.dfyang.staticizepage.controller;

import com.dfyang.staticizepage.pojo.User;
import com.dfyang.staticizepage.utils.StaticizePageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(Model model) {
        return "/static/user";
    }

    @GetMapping("/staticizeUser")
    public String staticizeUser(Model model) {
        // 模拟从数据库中查询用户
        User user = new User("user", "upass");
        model.addAttribute("user", user);
        return "/user";
    }

    @GetMapping("/generate")
    @ResponseBody
    public String generate() {
        StaticizePageUtil.staticize("http://localhost:8080/user", "user.html");
        return "生成静态页面成功";
    }

}
