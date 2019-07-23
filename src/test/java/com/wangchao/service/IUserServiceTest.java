package com.wangchao.service;

import com.wangchao.meta.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author wangchao
 * @date 2019/2/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class IUserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    public void getUserByName() {
        String userName = "buyer";
        User user = userService.getUserByName(userName);
        assertNotNull(user);
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getUserType());
    }
}