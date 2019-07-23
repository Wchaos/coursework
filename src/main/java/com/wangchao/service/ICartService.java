package com.wangchao.service;

import com.wangchao.meta.Cart;

import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
public interface ICartService {

    int addContentById(int userId, int contentId, int num);

    List<Cart> getCartListByBuyerId(int userId);

    int updateCartCount(int cartId, int count);

    int deleteCartByCartId(int cartId);

    int deleteCartByBuyerId(int userId);

}
