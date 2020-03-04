package com.qjfcc.deadlock.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author 秦江峰
 * @AddTime 2020/3/4 15:27
 */
@Data
@AllArgsConstructor
public class Account {
    private String name;
    /**
     * 最大使用资源
     */
    private List<ResourceEntity> max;
    /**
     * 已使用资源
     */
    private List<ResourceEntity> used;

    public Account() {
        this(Lists.newArrayList(
                new ResourceEntity(ResourceEnum.resource1, 0),
                new ResourceEntity(ResourceEnum.resource2, 0),
                new ResourceEntity(ResourceEnum.resource3, 0)
        ));
    }


    public Account(List<ResourceEntity> max, List<ResourceEntity> used) {
        this("", max, used);
    }

    public Account(List<ResourceEntity> max) {
        this(max, Lists.newArrayList(
                new ResourceEntity(ResourceEnum.resource1, 0),
                new ResourceEntity(ResourceEnum.resource2, 0),
                new ResourceEntity(ResourceEnum.resource3, 0)
        ));
    }


    /**
     * 获取所需资源数
     *
     * @return
     */
    public List<ResourceEntity> getNeed() {
        return Lists.newArrayList(
                new ResourceEntity(ResourceEnum.resource1, queryNeed(ResourceEnum.resource1)),
                new ResourceEntity(ResourceEnum.resource2, queryNeed(ResourceEnum.resource2)),
                new ResourceEntity(ResourceEnum.resource3, queryNeed(ResourceEnum.resource3))

        );
    }


    private int queryNeed(ResourceEnum resourceEnum) {
        int m = max.stream().filter(r -> r.getResourceEnum() == resourceEnum).findFirst().map(ResourceEntity::getCount).orElse(0);
        int u = used.stream().filter(r -> r.getResourceEnum() == resourceEnum).findFirst().map(ResourceEntity::getCount).orElse(0);
        return m - u;
    }

    /**
     * 重置已使用资源数量
     */
    public void initUsed(){

        this.used = Lists.newArrayList(
                new ResourceEntity(ResourceEnum.resource1, 0),
                new ResourceEntity(ResourceEnum.resource2, 0),
                new ResourceEntity(ResourceEnum.resource3, 0)
        );
    }

}
