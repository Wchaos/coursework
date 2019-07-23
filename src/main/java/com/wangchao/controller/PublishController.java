package com.wangchao.controller;

import com.wangchao.meta.Content;
import com.wangchao.meta.User;
import com.wangchao.service.IContentService;
import com.wangchao.utils.ReturnMsg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangchao
 * @date 2019/2/21
 */
@Controller
public class PublishController {

    @Resource
    private IContentService contentService;


    @RequestMapping("/publish")
    public String publishContent(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        return "publish";
    }

    @RequestMapping(value = "/publishSubmit", method = RequestMethod.POST)
    public String publishSubmit(Content content, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() == null) {
            return "login";
        }
        if(content==null) {
            return "publishSubmit";
        }
        content.setSellerId(user.getId());
        contentService.publishNewContent(content);
        model.addAttribute("user", user);
        if(content.getId()!=null) {
            model.addAttribute("content", content);
        }
        return "publishSubmit";
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(Content content, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() == null) {
            return "login";
        }
        if(content==null) {
            return "editSubmit";
        }
        content.setSellerId(user.getId());
        int affectRow = contentService.updateContent(content);
        model.addAttribute("user", user);
        if(affectRow > 0) {
            model.addAttribute("content", content);
        }
        return "editSubmit";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取请求中的文件流
        MultipartFile multipartFile = multipartRequest.getFile("file");
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ReturnMsg.failedMsg();
        }
        // 获取文件的后缀
        String suffix = multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().lastIndexOf("."));
        // 使用UUID生成文件名称
        String newFileName = UUID.randomUUID().toString() + suffix;

        String path = request.getServletContext().getRealPath("img");
        File filePath = new File(path);
        if (!filePath.exists() || !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        File file = new File(filePath + File.separator + newFileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            return ReturnMsg.failedMsg();
        }
        String newFilePath = request.getContextPath() + "img/" + newFileName;
        return ReturnMsg.successMsg(newFilePath);

    }
}
