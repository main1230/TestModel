package com.zzl.top.common.http.impl;

import com.zzl.top.common.http.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzl
 * time: 2017/10/16 20:43.
 * 描述：
 */

public class BaseRequest implements IRequest {

    private String method = POST;
    private String url;
    private Map<String, String> header;
    private Map<String, Object> params;

    public BaseRequest(String url) {
        this.url = url;

        header = new HashMap();
        params = new HashMap<>();
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public void setHeader(String key, String value) {
        header.put(key, value);
    }

    @Override
    public void setParam(String key, Object value) {
        params.put(key, value);
    }

    @Override
    public void setParam(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String getUrl() {
        if (method.equals(GET)) {
            if (params != null && !params.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append(url).append("?1=1");
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                return sb.toString();
            }
        }
        return url;
    }

    @Override
    public Map<String, String> getHeader() {
        return header;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }
}
