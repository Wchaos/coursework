package com.wangchao.controller;

import com.wangchao.meta.Content;
import com.wangchao.meta.User;
import com.wangchao.service.IContentService;
import com.wangchao.utils.ReturnMsg;
import com.wangchao.utils.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author wangchao
 * @date 2019/2/21
 */
@Controller
public class ContentController {
    private static final String UN_PURCHASED_TYPE = "1";

    @Resource
    private IContentService contentService;


    @RequestMapping("/")
    public String getIndex(@RequestParam(value = "type",required = false) String type, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Content> contentList;
        if (user == null || user.getId() == null || user.getUserType() == null) {
            //用户未登录或者用户非法情况
            contentList = contentService.getAllContentList();
        } else if (user.getUserType() == UserType.BUYER.getValue()) {
            //用户为买家
            if (UN_PURCHASED_TYPE.equals(type)) {
                //仅显示未购买内容
                contentList = contentService.getUnpurchasedContentListById(user.getId());
            } else {
                contentList = contentService.getBuyerContentListById(user.getId());
            }
        } else {
            //用户为卖家
            contentList = contentService.getSellerContentListById(user.getId());
        }
        model.addAttribute("user", user);
        model.addAttribute("contentList", contentList);
        return "index";
    }

    @RequestMapping("/show")
    public String showContent(@RequestParam("id") int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Content content = null;
        if (user != null && user.getId() != null && user.getUserType() == UserType.BUYER.getValue()) {
            content = contentService.getContentByBuyerIdAndContentId(user.getId(), id);
        } else {
            content = contentService.getContentById(id);
        }
        model.addAttribute("user", user);
        model.addAttribute("content", content);

        return "show";
    }

    @RequestMapping("/deleteContent")
    @ResponseBody
    public Map<String, Object> deleteContent(@RequestParam("id") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() == null || user.getUserType() != UserType.SELLER.getValue()) {
            //非卖家直接返回
            return ReturnMsg.failedMsg();
        }
        int affectRow = contentService.deleteContentByContentId(id);
        return affectRow > 0 ? ReturnMsg.successMsg() : ReturnMsg.failedMsg();
    }

    @RequestMapping("/edit")
    public String editContent(@RequestParam("id") int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        Content content = contentService.getContentById(id);
        model.addAttribute("user", user);
        model.addAttribute("content", content);
        return "edit";
    }


}
