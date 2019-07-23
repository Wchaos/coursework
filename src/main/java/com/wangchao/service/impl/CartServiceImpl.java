package com.wangchao.service.impl;

import com.wangchao.dao.CartDao;
import com.wangchao.meta.Cart;
import com.wangchao.service.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
@Service("cartService")
public class CartServiceImpl implements ICartService {

    @Resource
    private CartDao cartDao;

    /**
     * 添加内容到指定用户的购物车
     * @param userId 用户Id
     * @param contentId 内容Id
     * @param num 添加数量
     * @return 数据库影响行数
     */
    public int addContentById(int userId, int contentId, int num) {
        return cartDao.addContentById(userId, contentId, num);
    }

    /**
     * 获得用户购物车列表
     * @param userId 用户Id
     * @return 购物车列表
     */
    public List<Cart> getCartListByBuyerId(int userId) {

        return cartDao.getCartListByBuyerId(userId);
    }

    /**
     * 更新购物车中内容的数量
     * @param cartId 购物车Id
     * @param count 内容数量
     * @return 数据库影响行数
     */
    public int updateCartCount(int cartId, int count) {
        return cartDao.updateCartCount(cartId,count);
    }

    /**
     * 根据购物车Id,删除购物车
     * @param cartId 购物车Id
     * @return 数据库影响行数
     */
    public int deleteCartByCartId(int cartId) {
        return cartDao.deleteCartByCartId(cartId);
    }

    /**
     * 根据用户Id,删除用户的所有购物车
     * @param userId 用户Id
     * @return 数据库影响行数
     */
    public int deleteCartByBuyerId(int userId) {
        return cartDao.deleteCartByBuyerId(userId);
    }
}
