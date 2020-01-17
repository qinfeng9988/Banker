package com.qjfcc.deadlock;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author 秦江峰
 * @AddTime 2020/1/17 17:02
 */
@Data
public class ProcessCount {
    private String key;
    private Integer[] resouce;

    public ProcessCount(String key, Integer[] resouce) {
        this.key = key;
        this.resouce = resouce;
    }

    public boolean equals(ProcessCount p) {
        return p.getKey().equals(this.key);
    }
}
