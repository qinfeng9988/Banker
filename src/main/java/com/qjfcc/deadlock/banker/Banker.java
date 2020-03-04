package com.qjfcc.deadlock.banker;

import com.qjfcc.deadlock.dto.MyProcess;
import com.qjfcc.deadlock.dto.ResourceEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 秦江峰
 * @AddTime 2020/3/4 16:45
 */
public class Banker {

    Map<String, ResourceEntity> avaiableMaps = new HashMap<>();
    Map<String, MyProcess> processMap = new HashMap<>();

    /**
     * 模拟可用资源数
     *
     * @param avaiable
     */
    public Banker(List<ResourceEntity> avaiable) {
        for (ResourceEntity resourceEntity : avaiable) {
            avaiableMaps.put(resourceEntity.getResourceEnum().name(), resourceEntity);
        }
    }

    /**
     * 增加多个进程，模拟资源竞争
     *
     * @param process
     */
    public void addProcess(MyProcess process) {
        this.processMap.put(process.getName(), process);
    }


    public void borrow() throws Exception {
        if (processMap.size() == 0) {
            throw new Exception("未添加竞争资源");
        }
        boolean isfinish = true;
        MyProcess myProcess = null;
        for (Map.Entry<String, MyProcess> entry : processMap.entrySet()) {
            if (!entry.getValue().isFinish()) {
                isfinish = false;
                boolean isSafe = check(entry.getValue());
                if (isSafe) {
                    myProcess = entry.getValue();
                    break;
                }
            }
        }
        if (isfinish) {
            System.out.println("银行家算法执行完成");
            return;
        }
        if (myProcess == null) {
            System.out.println("已死锁，无法分配资源");
            return;
        }
        give(myProcess);
        //region 模拟执行程序
        Thread.sleep(2000);
        //endregion
        pay(myProcess);
        processMap.put(myProcess.getName(), myProcess);
        borrow();


    }

    /**
     * 检查某个资源的进程的出借，是否会引起系统进入不安全状态
     *
     * @return
     */
    private boolean check(MyProcess myProcess) {
        boolean isSafe = true;
        List<ResourceEntity> need = myProcess.getAccount().getNeed();
        for (ResourceEntity r : need) {
            int a = Optional.ofNullable(avaiableMaps.get(r.getResourceEnum().name()))
                    .orElse(new ResourceEntity(r.getResourceEnum(), 0))
                    .getCount();
            if (r.getCount() > a) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    /**
     * 从可用池中 出借 资源
     * 1、扣减可用池中的资源
     * 2、增加进程中的已使用资源
     *
     * @param myProcess
     * @throws Exception
     */
    private void give(MyProcess myProcess) throws Exception {
        List<ResourceEntity> needList = myProcess.getAccount().getNeed();
        for (ResourceEntity r : needList) {
            ResourceEntity resourceEntity = Optional.ofNullable(avaiableMaps.get(r.getResourceEnum().name()))
                    .orElse(new ResourceEntity(r.getResourceEnum(), 0));
            int avaiableResource = resourceEntity.getCount() - r.getCount();
            if (avaiableResource < 0) {
                throw new Exception("资源不足");
            }
            resourceEntity.setCount(avaiableResource);
            avaiableMaps.put(r.getResourceEnum().name(), resourceEntity);
        }
        //增加进程已经使用资源数至最大
        myProcess.getAccount().setUsed(myProcess.getAccount().getMax());
    }

    /**
     * 偿还资源（1、银行库增加可用资源 2、清空进程已使用资源）
     *
     * @param myProcess
     */
    private void pay(MyProcess myProcess) {
        List<ResourceEntity> used = myProcess.getAccount().getUsed();

        avaiableMaps.forEach((k, v) -> used.stream().filter(u -> u.getResourceEnum() == v.getResourceEnum()).findFirst().ifPresent(r -> v.setCount(v.getCount() + r.getCount())));

        myProcess.getAccount().initUsed();
        myProcess.setFinish(true);
    }
}
