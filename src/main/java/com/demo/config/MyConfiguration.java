package com.demo.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author:wangyupeng
 * @Date:2020/3/30
 * @Time:10:10 下午
 * @desc:
 **/
@Data
@Component
@ConfigurationProperties(prefix = "schedule")
@EqualsAndHashCode(callSuper = false)
public class MyConfiguration {
    private String corn1;
    private String corn2;
}
