package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author:wangyupeng
 * @Date:2020/3/30
 * @Time:10:10 下午
 * @desc:
 **/
@Slf4j
@SpringBootApplication
public class Application {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener
    public void afterApplicationStarted(ApplicationReadyEvent event) {
        String port = this.env.getProperty("server.port");
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            log.info("Spring-boot Application[{}] in host[{}]-port[{}]", "Started", host, port);
        } catch (UnknownHostException e) {
            log.error("获取本机地址发生异常", e);
        }
    }
}