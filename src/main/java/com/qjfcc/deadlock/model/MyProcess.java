package com.qjfcc.deadlock.model;

import lombok.Data;

import java.util.List;

/**
 * @author 秦江峰
 * @AddTime 2020/3/4 16:03
 */
@Data
public class MyProcess {
    private String name;
    private Account account;

    private boolean isFinish;
    public MyProcess(String _name, List<ResourceEntity> max, List<ResourceEntity> used){
        this.name = _name;
        this.account = new Account(_name,max,used);
        this.isFinish = false;
    }
}
