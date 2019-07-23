package com.wangchao.dao;

import com.wangchao.meta.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
public interface OrderDao {


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content",column = "contentId",
                    one = @One(select = "com.wangchao.dao.ContentDao.getContentById")),
            @Result(property = "count", column = "count"),
            @Result(property = "price", column = "price"),
            @Result(property = "date", column = "orderDate")
    })
    @Select("select * from orders where buyerId = #{userId}")
    List<Order> getOrderListByBuyerId(int userId);


    @Insert("insert into orders(contentId, buyerId, count, orderDate, price) " +
            "select cart.contentId, cart.buyerId, cart.count, Now(), content.price " +
            "from cart left join content on cart.contentId = content.id and content.beDeleted = 0 " +
            "where cart.buyerId = #{userId}")
    int insertOrdersFromCartByBuyerId(int userId);


}
