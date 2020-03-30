package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author:wangyupeng
 * @Date:2020/3/30
 * @Time:10:10 下午
 * @desc:
 **/
@Data
@AllArgsConstructor
public class Result {
    private int status;
    private Object data;

    public static Result getSuccessResult() {
        return new Result(0, "成功");
    }

    public static Result getFailedResult() {
        return new Result(1, "失败");
    }

    public static Result getSuccessResult(Object data) {
        return new Result(0, data);
    }

    public static Result getFailedResult(Object data) {
        return new Result(1, data);
    }
}
