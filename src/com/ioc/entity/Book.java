package com.ioc.entity;

import org.omg.PortableInterceptor.ServerRequestInfo;

/**
 * @author : avloger
 * @date : 2021/11/11 15:51
 */
public class Book {
    private int id;
    private String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
