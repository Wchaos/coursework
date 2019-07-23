package com.wangchao.controller;

import com.wangchao.meta.User;
import com.wangchao.service.IUserService;
import com.wangchao.utils.ReturnMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author wangchao
 * @date 2019/2/21
 */
@Controller
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/onLogin")
    @ResponseBody
    public Map<String, Object> onLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {
        User user = userService.getUserByName(userName);
        if (user != null && password.equals(user.getPassword())) {
            //登陆成功，将用户加入session
            session.setAttribute("user",user);
            return ReturnMsg.successMsg();
        }
        return ReturnMsg.failedMsg();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
