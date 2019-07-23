package com.wangchao.service;

import com.wangchao.meta.Order;

import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
public interface IOrderService {


    int insertOrdersFromCartByBuyerId(int userId);

    List<Order> getOrderListByBuyerId(int userId);

}
