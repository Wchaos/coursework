package com.wangchao.dao;

import com.wangchao.meta.Content;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wangchao
 * @date 2019/2/20
 */
public interface ContentDao {


    @Select("select * from content where beDeleted = 0")
    List<Content> getAllContentList();

    /**
     * 表中字段名与对象名称一致的，不用写@Result映射
     * @param userId
     * @return
     */
    @Results({
//            @ReturnMsg(property = "id", column = "id"),
//            @ReturnMsg(property = "title", column = "title"),
//            @ReturnMsg(property = "cAbstract", column = "cAbstract"),
//            @ReturnMsg(property = "text", column = "text"),
//            @ReturnMsg(property = "imgPath",column = "imgPath"),
//            @ReturnMsg(property = "price", column = "price"),
//            @ReturnMsg(property = "sellerId",column = "sellerId"),
            @Result(property = "bePurchased", column = "buyerId")
    })
    @Select("select c.*, o.buyerId  from content c left join orders o " +
            "on c.id = o.contentId and o.buyerId = #{userId} " +
            "where c.beDeleted = 0 " +
            "group by c.id" )
    List<Content> getBuyerContentList(int userId);


    @Results({
            @Result(property = "bePurchased", column = "soldCount")
    })
    @Select("select c.*, sum(o.count) as soldCount from content c left join orders o " +
            "on c.id = o.contentId " +
            "where sellerId = #{userId} and beDeleted = 0 " +
            "group by c.id")
    List<Content> getSellerContentList(int userId);


    @Select("select c.* from content c where c.beDeleted = 0 " +
            "and c.id not in (select o.contentId from orders o where o.buyerId = #{userId})")
    List<Content> getUnpurchasedContentList(int userId);


    @Select("select * from content where id = #{contentId} and beDeleted = 0")
    Content getContentById(int contentId);


    @Results({
            @Result(property = "bePurchased", column = "orderPrice")
    })
    @Select("select c.*,o.price as orderPrice from content c left join orders o " +
            "on c.id = o.contentId and o.buyerId = #{buyerId} " +
            "where c.id = #{contentId} and c.beDeleted = 0 " +
            "order by o.orderDate LIMIT 1")
    Content getContentByBuyerIdAndContentId(@Param("buyerId") int buyerId, @Param("contentId") int contentId);

    @Update("update content set beDeleted = 1 where id = #{contentId}")
    int deleteContentByContentId(int contentId);

    @Update("update content set title = #{title}, CAbstract = #{cAbstract}, " +
            "text = #{text}, imgPath = #{imgPath}, price = #{price}, sellerId = #{sellerId} " +
            "where id = #{id}")
    int updateContent(Content content);

    @Insert("insert into content(title, cAbstract, text, imgPath, price, sellerId)" +
            "values(#{title}, #{cAbstract}, #{text},#{imgPath}, #{price},#{sellerId})")
    @Options(useGeneratedKeys=true, keyColumn="id")
    int insertContent(Content content);


}
