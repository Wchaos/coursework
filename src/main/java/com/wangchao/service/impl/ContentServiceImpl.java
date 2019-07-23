package com.wangchao.service.impl;

import com.wangchao.dao.ContentDao;
import com.wangchao.meta.Content;
import com.wangchao.service.IContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
@Service("contentService")
public class ContentServiceImpl implements IContentService {

    @Resource
    private ContentDao contentDao;

    /**
     * 获得所有未删除的内容
     * @return 内容列表，内容bePurchased字段默认0，未购买
     */
    public List<Content> getAllContentList() {
        return contentDao.getAllContentList();
    }

    /**
     * 获取卖家发布的所有内容
     * @param userId 卖家Id
     * @return 内容列表，内容bePurchased字段赋值为内容已卖出总数量
     */
    public List<Content> getSellerContentListById(int userId) {
        return contentDao.getSellerContentList(userId);
    }

    /**
     * 获取所有内容，但是会根据买家id，判断内容是否被购买
     * @param userId 买家id
     * @return 内容列表，内容bePurchased字段赋值为买家id
     */
    public List<Content> getBuyerContentListById(int userId) {
        return contentDao.getBuyerContentList(userId);
    }

    /**
     * 获取用户未购买的内容
     * @param userId 买家id
     * @return 内容列表，内容bePurchased字段默认0，未购买
     */
    public List<Content> getUnpurchasedContentListById(int userId) {
        return contentDao.getUnpurchasedContentList(userId);
    }

    /**
     * 根据内容id获取指定内容
     * @param id 内容id
     * @return
     */
    public Content getContentById(int id) {
        return contentDao.getContentById(id);
    }

    /**
     * 获取指定内容，同时根据买家id，获取买家最近一次购买该内容的价格
     * @param buyerId 买家id
     * @param contentId 内容id
     * @return 指定内容详情，内容bePurchased字段赋值为买家最近一次购买该内容的价格
     */
    public Content getContentByBuyerIdAndContentId(int buyerId, int contentId) {
        return contentDao.getContentByBuyerIdAndContentId(buyerId, contentId);
    }

    /**
     * 删除指定内容
     * @param contentId 内容id
     * @return
     */
    public int deleteContentByContentId(int contentId) {
        return contentDao.deleteContentByContentId(contentId);
    }

    /**
     * 发布新的内容
     * @param content
     * @return
     */
    public int publishNewContent(Content content) {
        return contentDao.insertContent(content);
    }

    /**
     * 更新内容详情
     * @param content
     * @return
     */
    public int updateContent(Content content) {
        return contentDao.updateContent(content);
    }
}
