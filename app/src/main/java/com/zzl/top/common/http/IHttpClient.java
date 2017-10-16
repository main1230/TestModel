package com.zzl.top.common.http;

/**
 * Created by zhangzl
 * time: 2017/10/16 20:40.
 * 描述：抽象接口
 */

public interface IHttpClient {
    IResponse get(IRequest request);

    IResponse post(IRequest request);

    IResponse postJson(IRequest request);

    IResponse get(IRequest request, boolean forceCache);

    IResponse post(IRequest request, boolean forceCache);

    IResponse postJson(IRequest request, boolean forceCache);
}
