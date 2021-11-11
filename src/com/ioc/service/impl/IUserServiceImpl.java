package com.ioc.service.impl;

import com.ioc.Annotation.AutoWired;
import com.ioc.Annotation.Bean;
import com.ioc.dao.IUserDao;
import com.ioc.dao.Impl.UserDaoImpl;
import com.ioc.entity.User;
import com.ioc.service.IUserService;

/**
 * @author : avloger
 * @date : 2021/11/11 15:42
 */
@Bean
public class IUserServiceImpl implements IUserService {
    @AutoWired
    private IUserDao userDao;
    @Override
    public void login() {
        userDao.findUserById(1);
        System.out.println("这是登录业务的实现");
    }

    @Override
    public void regist() {
        userDao.saveUser(new User("1","12","123",123));
        System.out.println("注册业务实现方法");
    }
}
