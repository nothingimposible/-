package com.qst.studyingcourse.controller;

import com.qst.studyingcourse.mapper.UserMapper;
import com.qst.studyingcourse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/login")
    public String login(User user, HttpSession session, Model model){
        User us=userMapper.login(user);
        if(us!=null){
            session.setAttribute("user",us.getFirstname()+us.getLastname());
            session.setAttribute("privilege",us.getPrivilege());
            return "redirect:/course/main";
        }else{
            model.addAttribute("loginmsg","密码错误或者用户名不存在！");
            return "login";
        }
    }

    @RequestMapping(value = "/register")
    public String register(User user){
        if (user.getID()!=null){
            userMapper.insertUser(user);
            return "redirect:/user/login";
        }else{
            return "register";
        }

    }

    @RequestMapping(value = "/destroy")
    public String destroy(HttpSession session){
        session.invalidate();
        return "redirect:/course/main";
    }
}
