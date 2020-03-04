package com.qjfcc.deadlock.dto;

/**
 * @author 秦江峰
 * @AddTime 2020/3/4 15:34
 */
public enum ResourceEnum {
    resource1("resource1"),
    resource2("resource2"),
    resource3("resource3");


    private String name;

    ResourceEnum(String _name){
        this.name = _name;
    }
}
