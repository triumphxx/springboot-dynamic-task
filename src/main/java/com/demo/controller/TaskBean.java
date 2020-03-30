package com.demo.controller;

import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author:wangyupeng
 * @Date:2020/3/30
 * @Time:10:10 下午
 * @desc:
 **/
@Component
public class TaskBean {

    public static ScheduledFuture<?> future;

    public void test(){
        System.out.println("任务正在进行中");
    }


}
