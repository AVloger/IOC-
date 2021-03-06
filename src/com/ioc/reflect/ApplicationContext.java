package com.ioc.reflect;

import com.ioc.Annotation.Bean;
import com.ioc.Annotation.AutoWired;
import com.ioc.dao.IUserDao;
import com.ioc.dao.Impl.UserDaoImpl;
import com.ioc.service.IUserService;
import com.ioc.service.impl.IUserServiceImpl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : avloger
 * @date : 2021/11/11 17:43
 */
public class ApplicationContext<T> {
    private HashMap<Class,Object> BeanFactory = new HashMap<>();
    private String filePath;

    public T getBean(Class clazz) {
        return (T)BeanFactory.get(clazz);
    }

    public void initContext() {
        BeanFactory.put(IUserDao.class,new UserDaoImpl());
        BeanFactory.put(IUserService.class,new IUserServiceImpl());
    }
    public void initContextAnnotation() {
        /**
         * 扫描包
         */

    }
    //加载全部的类的实例
    public  void initContextByAnnotation()  {
        filePath = ApplicationContext.class.getClassLoader().getResource("").getFile();
        loadOne(new File(filePath));
        assembleObject();
    }
    //是不是给所有的字符赋值
    private void assembleObject() {
        for(Map.Entry<Class,Object> entry : BeanFactory.entrySet()){
            //就是咱们放在容器的对象
            Object obj = entry.getValue();
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields){
                AutoWired annotation = field.getAnnotation(AutoWired.class);
                if( annotation != null ){
                    field.setAccessible(true);
                    try {
                        System.out.println("正在给【"+obj.getClass().getName()+"】属性【" + field.getName() + "】注入值【"+ BeanFactory.get(field.getType()).getClass().getName() +"】");
                        field.set(obj,BeanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * 加载一个文件夹的类
     * @param fileParent
     */
    private  void loadOne(File fileParent) {
        if (fileParent.isDirectory()) {
            File[] childrenFiles = fileParent.listFiles();
            if(childrenFiles == null || childrenFiles.length == 0){
                return;
            }
            for (File child : childrenFiles) {
                if (child.isDirectory()) {
                    //如果是个文件夹就继续调用该方法,使用了递归
                    loadOne(child);
                } else {
                    //通过文件路径转变成全类名,第一步把绝对路径部分去掉
                    //  D:\mytools
                    //  com\xinzhi\dao\UserDao.class
                    String pathWithClass = child.getAbsolutePath().substring(filePath.length() - 1);
                    //选中class文件
                    if (pathWithClass.contains(".class")) {
                        //    com.xinzhi.dao.UserDao
                        //去掉.class后缀，并且把 \ 替换成 .
                        String fullName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");
                        try {
                            Class<?> aClass = Class.forName(fullName);

                            //把非接口的类实例化放在map中
                            if(!aClass.isInterface()){
                                Bean annotation = aClass.getAnnotation(Bean.class);
                                if(annotation != null){
                                    Object instance = aClass.newInstance();
                                    //判断一下有没有接口
                                    if(aClass.getInterfaces().length > 0) {
                                        //如果有接口把接口的class当成key，实例对象当成value
                                        System.out.println("正在加载【"+ aClass.getInterfaces()[0] +"】,实例对象是：" + instance.getClass().getName());
                                        BeanFactory.put(aClass.getInterfaces()[0], instance);
                                    }else{
                                        //如果有接口把自己的class当成key，实例对象当成value
                                        System.out.println("正在加载【"+ aClass.getName() +"】,实例对象是：" + instance.getClass().getName());
                                        BeanFactory.put(aClass, instance);
                                    }
                                }
                            }
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
