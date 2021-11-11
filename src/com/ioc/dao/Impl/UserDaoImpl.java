package com.ioc.dao.Impl;

import com.ioc.Annotation.Bean;
import com.ioc.dao.IUserDao;
import com.ioc.entity.User;

import java.util.List;

/**
 * @author : avloger
 * @date : 2021/11/11 15:49
 */
@Bean
public class UserDaoImpl implements IUserDao {
    @Override
    public User findUserById(int id) {
        System.out.println("这里是Dao");
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public List<User> finUsersByUserName() {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }
}
