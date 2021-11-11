package com.ioc;

import com.ioc.dao.IUserDao;
import com.ioc.reflect.ApplicationContext;
import com.ioc.service.IUserService;
import javafx.beans.binding.ObjectExpression;

/**
 * @author : avloger
 * @date : 2021/11/11 17:53
 */
public class BootStrap {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.initContextByAnnotation();
        Object bean = applicationContext.getBean(IUserService.class);

        System.out.println(bean);
    }
}
