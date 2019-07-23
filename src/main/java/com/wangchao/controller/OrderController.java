package com.wangchao.controller;

import com.wangchao.meta.Order;
import com.wangchao.meta.User;
import com.wangchao.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/21
 */
@Controller
public class OrderController {

    @Resource
    private IOrderService orderService;


    @RequestMapping("/account")
    public String showOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() == null) {
            return "login";
        }
        List<Order> orderList = orderService.getOrderListByBuyerId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orderList", orderList);
        return "account";
    }


}
