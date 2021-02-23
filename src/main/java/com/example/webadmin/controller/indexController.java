package com.example.webadmin.controller;

import com.example.webadmin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class indexController {

    @GetMapping(value = {"/","/login"})
    public String loginPage(){

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        if(!StringUtils.isEmpty(user.getUserName()) && "123456".equals(user.getPassword())){
            session.setAttribute("loginUser",user);
            //重定向到main页面,防止表单重复提交
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","帐号或密码错误");
            return "login";
        }
    }

    /**
     * 去main页面
     * 加.html后缀为页面跳转
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
//        Object loginUser = session.getAttribute("loginUser");
//        if(loginUser != null){
//            return "main";
//        }else{
//            model.addAttribute("msg","请先登录");
//            return "login";
//        }
        return "main";
    }
}
