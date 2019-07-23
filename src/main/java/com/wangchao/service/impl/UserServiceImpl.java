package com.wangchao.service.impl;

import com.wangchao.dao.UserDao;
import com.wangchao.meta.User;
import com.wangchao.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangchao
 * @date 2019/2/20
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource()
    private UserDao userDao;

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return
     */
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }
}
