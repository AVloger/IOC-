package com.ioc.dao;

import com.ioc.entity.User;

import java.util.List;

/**
 * @author : avloger
 * @date : 2021/11/11 15:45
 */
public interface IUserDao {
    /**
     * 根据id找到一个user
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 获取所有的用户
     * @return
     */
    List<User> findAllUsers();

    /**
     * 根据用户名获取用户
     * @return
     */
    List<User> finUsersByUserName();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

}
