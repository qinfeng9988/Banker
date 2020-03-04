package com.qjfcc.deadlock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 秦江峰
 * @AddTime 2020/3/4 15:37
 */
@Data
@AllArgsConstructor
public class ResourceEntity {
    private ResourceEnum resourceEnum;
    private int count;
}
