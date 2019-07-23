package com.wangchao.dao;

import com.wangchao.meta.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
public interface CartDao {

    @Insert("insert into cart(buyerId, contentId, count) " +
            "values(#{userId}, #{contentId}, #{num}) " +
            "on duplicate key update count = count + #{num}")
    int addContentById(@Param("userId") int userId, @Param("contentId") int contentId, @Param("num") int count);


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "buyerId", column = "buyerId"),
            @Result(property = "content", column = "contentId",
                    one = @One(select = "com.wangchao.dao.ContentDao.getContentById")),
            @Result(property = "count", column = "count")
    })
    @Select("select * from cart where buyerId = #{userId}")
    List<Cart> getCartListByBuyerId(int userId);

    @Update("update cart set count = #{count} where id = #{cartId}")
    int updateCartCount(@Param("cartId") int cartId, @Param("count") int count);

    @Delete("delete from cart where id = #{cartId}")
    int deleteCartByCartId(int cartId);

    @Delete("delete from cart where buyerId = #{userId}")
    int deleteCartByBuyerId(int userId);
}
