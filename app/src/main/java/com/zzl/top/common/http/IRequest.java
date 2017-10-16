package com.zzl.top.common.http;

import java.util.Map;

/**
 * Created by zhangzl
 * time: 2017/10/16 20:41.
 * 描述：定义请求数据的封装方式
 */

public interface IRequest {
    public static final String POST = "POST";
    public static final String GET = "GET";

    /**
     * 指定请求方式
     */
    void setMethod(String method);

    /**
     * 指定请求头部
     * @param key
     * @param value
     */
    void setHeader(String key, String value);

    /**
     * 指定请求参数
     * @param key
     * @param value
     */
    void setParam(String key, Object value);

    /**
     * 提供给执行库请求行URL
     * @return
     */
    String getUrl();

    /**
     * 提供给执行库请求头部
     * @return
     */
    Map<String, String> getHeader();

    /**
     * 提供给执行库请求参数
     * @return
     */
    Map<String, Object> getParams();
}
