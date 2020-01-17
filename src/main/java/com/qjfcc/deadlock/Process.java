package com.qjfcc.deadlock;

/**
 * @author 秦江峰
 * @AddTime 2020/1/17 16:23
 */
public class Process {
    /**
     * 进程名称
     */
    private String name;

    /**
     * a类资源需求数
     */
    private Integer a;
    /**
     * b 类资源需求数
     */
    private Integer b;
    /**
     * c 类资源需求数
     */
    private Integer c;
    /**
     * d 类资源需求数
     */
    private Integer d;

    public Process(int a, int b, int c, int d, String name) {
        this.a = a;
        this.b = b;
        this.d = d;
        this.c = c;
        this.name = name;
    }

    public Integer[] queryMax() {
        return new Integer[]{a, b, c, d};
    }

    public boolean equals(Process p) {
        return p.name.equals(this.name);
    }

    public String getName() {
        return name;
    }
}
