package com.wangchao.controller;

import com.wangchao.meta.Cart;
import com.wangchao.meta.User;
import com.wangchao.service.ICartService;
import com.wangchao.service.IOrderService;
import com.wangchao.utils.ReturnMsg;
import com.wangchao.utils.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchao
 * @date 2019/2/21
 */
@Controller
public class CartController {

    @Resource
    private ICartService cartService;
    @Resource
    private IOrderService orderService;

    @RequestMapping("/addToCart")
    @ResponseBody
    public Map<String, Object> addContentToCart(int contentId, int buyNum, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() == null) {
            return ReturnMsg.failedMsg("please login");
        }
        int affectRow = cartService.addContentById(user.getId(), contentId, buyNum);
        return affectRow > 0 ? ReturnMsg.successMsg() : ReturnMsg.failedMsg();
    }

    @RequestMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() == null) {
            return "login";
        }
        List<Cart> cartList = cartService.getCartListByBuyerId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @RequestMapping("/deleteCart")
    @ResponseBody
    public Map<String, Object> deleteCart(@RequestParam("cartId") int cartId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() != UserType.BUYER.getValue()) {
            return ReturnMsg.failedMsg();
        }
        int affectRow = cartService.deleteCartByCartId(cartId);
        return affectRow > 0 ? ReturnMsg.successMsg() : ReturnMsg.failedMsg();
    }

    @RequestMapping("/settle")
    @ResponseBody
    public Map<String, Object> settleAndClearCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() == null) {
            return ReturnMsg.failedMsg("please login");
        }
        int affectRow = orderService.insertOrdersFromCartByBuyerId(user.getId());
        return affectRow > 0 ? ReturnMsg.successMsg() : ReturnMsg.failedMsg();
    }

    @RequestMapping("/editCartCount")
    @ResponseBody
    public Map<String,Object> editCartCount(@RequestParam("cartId") int cartId, @RequestParam("count") int count,HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() != UserType.BUYER.getValue()) {
            return ReturnMsg.failedMsg();
        }
        int affectRow = cartService.updateCartCount(cartId,count);
        return affectRow > 0 ? ReturnMsg.successMsg() : ReturnMsg.failedMsg();
    }

}
