package com.demo.controller;

import com.demo.config.MyConfiguration;
import com.demo.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * @author:wangyupeng
 * @Date:2020/3/30
 * @Time:10:10 下午
 * @desc:
 **/
@Slf4j
@RestController
@RequestMapping("/task")
@Api(tags = "定时任务的启动与关闭")
public class DynamicTaskController {

    @Autowired
    private MyConfiguration configuration;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    TaskBean taskBean;


    private ScheduledFuture<?> future1;
    private ScheduledFuture<?> future2;
    private ScheduledFuture<?> future3;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @PostMapping("/startCron1")
    @ApiOperation("启动定时任务1")
    public Result startCron1() {
        if (future1 == null || future1.isCancelled()) {
            future1 = threadPoolTaskScheduler.schedule(() -> {
//                String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//                log.info("定时任务1, 现在时间是：{}", now);
                taskBean.test();
            }, triggerContext -> new CronTrigger(configuration.getCorn1()).nextExecutionTime(triggerContext));
            log.info("定时任务1已经启动！");
        }
        return Result.getSuccessResult();
    }

    @PostMapping("/changeCron1")
    @ApiOperation("修改定时任务1")
    public void changeCron1(String corn) { //corn表示定时参数，如：0/5 * * * * ?
        stopCron1(); //先关闭定时任务1，再创建新的定时任务1
        future1 = threadPoolTaskScheduler.schedule(() -> log.info("新的定时任务1"), new CronTrigger(corn));
        log.info("定时任务1修改成功！");
    }

    @PostMapping("/startCron2")
    @ApiOperation("启动定时任务2")
    public Result startCron2() {
        if (future2 == null || future2.isCancelled()) {
            future2 = threadPoolTaskScheduler.schedule(() -> {
                String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                log.info("定时任务2, 现在时间是：{}", now);
            }, triggerContext -> new CronTrigger(configuration.getCorn2()).nextExecutionTime(triggerContext));
            log.info("定时任务2已经启动！");
        }
        return Result.getSuccessResult();
    }

    @PostMapping("/startCron3")
    @ApiOperation("启动定时任务3")
    public void startCron3(String corn) {
        if (future3 == null || future3.isCancelled()) {
            future3 = threadPoolTaskScheduler.schedule(() -> log.info("定时任务3"), new CronTrigger(corn));
            log.info("定时任务3已经启动！");
        }
    }

    @PostMapping("/stopCron1")
    @ApiOperation("关闭定时任务1")
    public Result stopCron1() {
        if (future1 != null) {
            future1.cancel(true); //true表示如果定时任务正在执行也立即停止,false则等待任务结束后再停止
            if (future1.isCancelled()) log.info("定时任务1已经关闭！");
        }
        return Result.getSuccessResult();
    }

    @PostMapping("/stopCron2")
    @ApiOperation("关闭定时任务2")
    public Result stopCron2() {
        if (future2 != null) {
            future2.cancel(true);
            if (future2.isCancelled()) log.info("定时任务2已经关闭！");
        }
        return Result.getSuccessResult();
    }

    @PostMapping("/stopCron3")
    @ApiOperation("关闭定时任务3")
    public Result stopCron3() {
        if (future2 != null) {
            future2.cancel(true);
            if (future2.isCancelled()) log.info("定时任务3已经关闭！");
        }
        return Result.getSuccessResult();
    }



    @PostMapping("/startCron133")
    @ApiOperation("启动定时任务133")
    public Result startCron133() {
        ScheduledFuture<?> future3 = TaskBean.future;
        if (future3 == null || future3.isCancelled()){
            threadPoolTaskScheduler.schedule(() -> {
                taskBean.test();
            }, triggerContext -> new CronTrigger(configuration.getCorn1()).nextExecutionTime(triggerContext));
            log.info("定时任务133已经启动！");
        }
        return Result.getSuccessResult();
    }

    @PostMapping("/stopCron133")
    @ApiOperation("关闭定时任务133")
    public Result stopCron133() {
        ScheduledFuture<?> future1 = TaskBean.future;
        if (future1 != null) {
            future1.cancel(true); //true表示如果定时任务正在执行也立即停止,false则等待任务结束后再停止
            if (future1.isCancelled()) {
                log.info("定时任务133已经关闭！");
            }
        }
        return Result.getSuccessResult();
    }
}