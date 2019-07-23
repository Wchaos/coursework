package com.wangchao.service;

import com.wangchao.meta.Content;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangchao
 * @date 2019/2/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class IContentServiceTest {

    @Resource(name = "contentService")
    private IContentService contentService;

    @Test
    public void getAllContentList() {
        List<Content> allContentList = contentService.getAllContentList();
        Assert.assertNotNull(allContentList);
        for (Content content : allContentList){
            printContent(content);
        }
        System.out.println("contentListSize:"+allContentList.size());
    }

    @Test
    public void getSellerContentListById() {
        int sellerId = 2;
        List<Content> contentList = contentService.getSellerContentListById(sellerId);
        Assert.assertNotNull(contentList);
        for (Content content : contentList){
            printContent(content);
        }
        System.out.println("contentListSize:"+contentList.size());
    }

    @Test
    public void getBuyerContentListById() {
        int buyerId = 1;
        List<Content> contentList = contentService.getBuyerContentListById(buyerId);
        Assert.assertNotNull(contentList);
        for (Content content : contentList){
            printContent(content);
        }
        System.out.println("contentListSize:"+contentList.size());
    }

    @Test
    public void getUnpurchasedContentListById() {
        int buyerId = 1;
        List<Content> contentList = contentService.getUnpurchasedContentListById(buyerId);
        Assert.assertNotNull(contentList);
        for (Content content : contentList){
            printContent(content);
        }
        System.out.println("contentListSize:"+contentList.size());
    }

    @Test
    public void getContentById() {
        int contentId = 17;
        Content content = contentService.getContentById(contentId);
        Assert.assertNotNull(content);
        printContent(content);
    }

    @Test
    public void getContentByBuyerIdAndContentId() {
        int buyerId = 1;
        int contentId = 17;
        Content content = contentService.getContentByBuyerIdAndContentId(buyerId,contentId);
        Assert.assertNotNull(content);
        printContent(content);
    }

    @Test
    public void deleteContentByContentId() {
        int contentId = 21;
        int affectRow = contentService.deleteContentByContentId(contentId);
        System.out.println("affectRow: "+ affectRow);
        Assert.assertTrue(affectRow > 0);
    }

    @Test
    public void publishNewContent() {
        Content content = new Content();
        content.setTitle("测试5");
        content.setcAbstract("测试5摘要");
        content.setText("测试5正文");
        content.setImgPath("http://img0.imgtn.bdimg.com/it/u=3967239004,1951414302&fm=26&gp=0.jpg");
        content.setPrice(new BigDecimal(120000));
        content.setSellerId(2);
        int affectRow = contentService.publishNewContent(content);
        System.out.println("affectRow: "+ affectRow);
        Assert.assertTrue(affectRow > 0);
    }

    @Test
    public void updateContent() {
        int contentId = 22;
        Content content = contentService.getContentById(contentId);
        content.setPrice(new BigDecimal(150000.12));
        int affectRow = contentService.updateContent(content);
        System.out.println("affectRow: "+ affectRow);
        Assert.assertTrue(affectRow > 0);

    }

    private void printContent(Content content) {
        System.out.println("contentId:" + content.getId());
        System.out.println("contentTitle:" + content.getTitle());
        System.out.println("contentAbstract:"+ content.getcAbstract());
        System.out.println("contentText:"+content.getText());
        System.out.println("contentPrice:" + content.getPrice());
        System.out.println("contentImgPath: " + content.getImgPath());
        System.out.println("contentSellerId:"+ content.getSellerId());
        System.out.println("contentBeDeleted:"+content.getBeDeleted());
        System.out.println("ContentBePurchased:"+ content.getBePurchased());
        System.out.println();

    }
}