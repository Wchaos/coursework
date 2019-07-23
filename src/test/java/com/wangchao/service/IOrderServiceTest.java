package com.wangchao.service;

import com.wangchao.meta.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangchao
 * @date 2019/2/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class IOrderServiceTest {

    @Resource(name = "orderService")
    private IOrderService orderService;

    @Test
    public void insertOrdersFromCartByBuyerId() {
        int buyerId = 1;
        int affectRow = orderService.insertOrdersFromCartByBuyerId(buyerId);
        System.out.println("affectRow: "+ affectRow);
        Assert.assertTrue(affectRow > 0);
    }

    @Test
    public void getOrderListByBuyerId() {
        int buyerId = 1;
        List<Order> orderList = orderService.getOrderListByBuyerId(buyerId);
        Assert.assertNotNull(orderList);
        for (Order order : orderList){
            System.out.println("orderId："+order.getId());
            System.out.println("orderBuyerId: "+order.getBuyerId());
            System.out.println("orderCount:"+order.getCount());
            System.out.println("orderPrice:"+order.getPrice());
            System.out.println("orderDate:"+order.getDate());
            System.out.println("content in order：");
            System.out.println("contentId:"+ order.getContent().getId());
            System.out.println("contentTitle:"+ order.getContent().getTitle());
            System.out.println("contentPrice:"+ order.getContent().getPrice());
            System.out.println("contentImgPath: " + order.getContent().getImgPath());
            System.out.println();
        }
        System.out.println("orderListSize: " + orderList.size());
    }
}