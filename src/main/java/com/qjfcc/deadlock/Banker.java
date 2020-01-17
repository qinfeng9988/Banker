package com.qjfcc.deadlock;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 秦江峰
 * @AddTime 2020/1/17 15:07
 */
public class Banker {

    private Integer resourceCount;

    /**
     * 已分配资源
     */
    private List<ProcessCount> allocation;
    /**
     * 可用资源数
     */
    private Integer[] avaiable;
    /**
     * 每个进程 最大需求资源总数
     */
    private List<ProcessCount> max;
    /**
     * 当前需要的资源数
     */
    private List<ProcessCount> need;

    /**
     * @param resource 可用资源总数
     */
    public Banker(Integer[] resource) {
        this.resourceCount = resource.length;
        //初始化可用资源
        this.avaiable = resource;
    }

    /**
     * 添加 处理进程
     *
     * @param process
     */
    public void addProcess(Process process) {
        if (process.queryMax().length != resourceCount) {
            throw new IllegalArgumentException("参数 process 进程中的资源数量配置不符合 要求");
        }

        if (max == null) {
            max = Lists.newArrayList();
        }
        max.add(new ProcessCount(process.getName(), process.queryMax()));
    }

    /**
     * 出借
     * @param process 哪个进程来借，
     * @param processCount  借多少资源
     */
    public void borrow(Process process,ProcessCount processCount){

    }

    /**
     * 检查 是否安全状态
     * @param process
     * @param processCount
     * @return
     */
    public boolean check(Process process,ProcessCount processCount){
        return false;
    }


    /**
     * 偿还资源
     *
     * @param process
     */
    public void payBack(Process process) {
        if (CollectionUtils.isEmpty(this.allocation)) {
            return;
        }
        //将已分配资源放进可用资源中
        ProcessCount current = this.allocation.stream().filter(p -> p.getKey().equals(process.getName())).findFirst().orElse(null);
        for (int i = 0; i < avaiable.length; i++) {
            avaiable[i] += current.getResouce()[i];
        }
        //移除 最大需求总数
        this.max.remove(current);
        //移除 需求量
        this.need.remove(current);
        //移除 已分配量
        this.allocation.remove(current);

    }

}
