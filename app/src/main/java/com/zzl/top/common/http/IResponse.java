package com.zzl.top.common.http;

/**
 * Created by zhangzl
 * time: 2017/10/16 20:40.
 * 描述：
 */

public interface IResponse {
    /**
     * 状态码
     */
    int getCode();

    /**
     * 数据体
     */
    String getData();
}
