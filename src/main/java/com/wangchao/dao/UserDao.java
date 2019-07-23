package com.wangchao.dao;

import com.wangchao.meta.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author wangchao
 * @date 2019/2/20
 */
public interface UserDao {

    @Select("select * from user where userName = #{userName}")
    User getUserByName(String userName);

}
