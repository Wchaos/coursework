package com.wangchao.service;

import com.wangchao.meta.Cart;
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
public class ICartServiceTest {

    @Resource(name="cartService")
    private ICartService cartService;

    @Test
    public void addContentById() {
        int userId = 1;
        int contentId = 17;
        int num = 10;
        int affectRow = cartService.addContentById(userId,contentId,num);
        System.out.println("affectRow: " + affectRow);
        Assert.assertTrue(affectRow > 0);

    }

    @Test
    public void getCartListByBuyerId() {
        int useId = 1;
        List<Cart> cartList = cartService.getCartListByBuyerId(useId);
        Assert.assertNotNull(cartList);
        for(Cart cart : cartList){
            System.out.println("cartId: "+ cart.getId());
            System.out.println("buyerId: " + cart.getBuyerId());
            System.out.println("count :"+ cart.getCount());
            System.out.println("Content in cart:");
            System.out.println("contentId:"+ cart.getContent().getId());
            System.out.println("contentTitle:"+ cart.getContent().getTitle());
            System.out.println("contentPrice:"+ cart.getContent().getPrice());
            System.out.println("contentImgPath: " + cart.getContent().getImgPath());
        }
    }

    @Test
    public void updateCartCount(){
        int cartId = 63;
        int count = 3;
        int affectRow = cartService.updateCartCount(cartId,count);
        System.out.println("affectRow: " + affectRow);
        Assert.assertTrue(affectRow>0);

    }

    @Test
    public void deleteCartByCartId() {
        int cartId = 51;
        int affectRow = cartService.deleteCartByCartId(cartId);
        System.out.println("affectRow: " + affectRow);
        Assert.assertTrue(affectRow>0);
    }

    @Test
    public void deleteCartByBuyerId() {
        int buyerId = 1;
        int affactRow = cartService.deleteCartByBuyerId(buyerId);
        System.out.println("affactRow: " + affactRow);
        Assert.assertTrue(affactRow>0);
    }
}