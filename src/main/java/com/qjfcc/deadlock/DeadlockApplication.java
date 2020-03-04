package com.qjfcc.deadlock;

import com.google.common.collect.Lists;
import com.qjfcc.deadlock.banker.Banker;
import com.qjfcc.deadlock.dto.MyProcess;
import com.qjfcc.deadlock.dto.ResourceEntity;
import com.qjfcc.deadlock.dto.ResourceEnum;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeadlockApplication {

    public static void main(String[] args) throws Exception {

        Banker banker = new Banker(Lists.newArrayList(
                new ResourceEntity(ResourceEnum.resource1, 3),
                new ResourceEntity(ResourceEnum.resource2, 2),
                new ResourceEntity(ResourceEnum.resource3, 2)
        ));
//region 模拟资源竞争
        banker.addProcess(new MyProcess("p0",
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 7),
                        new ResourceEntity(ResourceEnum.resource2, 5),
                        new ResourceEntity(ResourceEnum.resource3, 3)
                ),
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 0),
                        new ResourceEntity(ResourceEnum.resource2, 1),
                        new ResourceEntity(ResourceEnum.resource3, 0)
                )
                ));
        banker.addProcess(new MyProcess("p1",
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 3),
                        new ResourceEntity(ResourceEnum.resource2, 2),
                        new ResourceEntity(ResourceEnum.resource3, 2)
                ),
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 2),
                        new ResourceEntity(ResourceEnum.resource2, 0),
                        new ResourceEntity(ResourceEnum.resource3, 0)
                )
        ));

        banker.addProcess(new MyProcess("p2",
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 9),
                        new ResourceEntity(ResourceEnum.resource2, 0),
                        new ResourceEntity(ResourceEnum.resource3, 2)
                ),
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 3),
                        new ResourceEntity(ResourceEnum.resource2, 0),
                        new ResourceEntity(ResourceEnum.resource3, 2)
                )
        ));

        banker.addProcess(new MyProcess("p3",
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 2),
                        new ResourceEntity(ResourceEnum.resource2, 2),
                        new ResourceEntity(ResourceEnum.resource3, 2)
                ),
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 2),
                        new ResourceEntity(ResourceEnum.resource2, 1),
                        new ResourceEntity(ResourceEnum.resource3, 1)
                )
        ));

        banker.addProcess(new MyProcess("p4",
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 4),
                        new ResourceEntity(ResourceEnum.resource2, 3),
                        new ResourceEntity(ResourceEnum.resource3, 3)
                ),
                Lists.newArrayList(
                        new ResourceEntity(ResourceEnum.resource1, 0),
                        new ResourceEntity(ResourceEnum.resource2, 0),
                        new ResourceEntity(ResourceEnum.resource3, 2)
                )
        ));
//endregion

        banker.borrow();
//        SpringApplication.run(DeadlockApplication.class, args);
    }

}
