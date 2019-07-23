package com.wangchao.service;

import com.wangchao.meta.Content;

import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
public interface IContentService {

    List<Content> getAllContentList();

    List<Content> getSellerContentListById(int userId);

    List<Content> getBuyerContentListById(int userId);

    List<Content> getUnpurchasedContentListById(int userId);

    Content getContentById(int id);

    Content getContentByBuyerIdAndContentId(int buyerId, int contentId);

    int deleteContentByContentId(int contentId);

    int publishNewContent(Content content);

    int updateContent(Content content);

}
