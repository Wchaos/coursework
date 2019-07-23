package com.wangchao.service.impl;

import com.wangchao.dao.CartDao;
import com.wangchao.dao.OrderDao;
import com.wangchao.meta.Order;
import com.wangchao.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private CartDao cartDao;

    /**
     * 此方法被配置成事务
     * 将购物车中的内容生成订单
     * 同时清空购物车
     * @param userId 买家id
     * @return
     */
    public int insertOrdersFromCartByBuyerId(int userId){
        orderDao.insertOrdersFromCartByBuyerId(userId);
        return cartDao.deleteCartByBuyerId(userId);
    }

    /**
     * 根据买家id，获取其名下订单列表
     * @param userId 买家id
     * @return
     */
    public List<Order> getOrderListByBuyerId(int userId) {
        return orderDao.getOrderListByBuyerId(userId);
    }

}
