package com.zzl.top.common.http.impl;

import com.alibaba.fastjson.JSON;
import com.zzl.top.Const;
import com.zzl.top.common.http.IHttpClient;
import com.zzl.top.common.http.IRequest;
import com.zzl.top.common.http.IResponse;
import com.zzl.top.utils.LogUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhangzl
 * time: 2017/10/16 20:50.
 * 描述：
 */

public class OkHttpClientImpl implements IHttpClient {
    public static final String TAG = "OkHttpClientImpl";

    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build();

    @Override
    public IResponse get(IRequest request) {
        // 指定请求方式
        request.setMethod(IRequest.GET);
        // 解析头部
        Map<String, String> header = request.getHeader();
        // OkHttp 的 Request.Builder
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            // 组装成 OkHttp 的 Header
            builder.header(key, header.get(key));

        }
        // 获取 url
        String url = request.getUrl();
        LogUtils.i(TAG, "--get url:" + url);
        builder.url(url).get();

        Request oKRequest = builder.build();
        // 执行 oKRequest
        return execute(oKRequest);
    }

    @Override
    public IResponse post(IRequest request) {
        // 指定请求方式
        request.setMethod(IRequest.POST);

        //添加header
        Request.Builder builder = new Request.Builder();
        Map<String, String> header = request.getHeader();
        for (Map.Entry<String, String> m : header.entrySet()) {
            builder.header(m.getKey(), m.getValue());
        }
        //添加参数
        FormBody.Builder formBuilder = new FormBody.Builder();
        Map<String, Object> params = request.getParams();
        for (Map.Entry<String, Object> m : params.entrySet()) {
            formBuilder.add(m.getKey(), m.getValue().toString());
        }

        String url = request.getUrl();
        LogUtils.i(TAG, "--get postjson:" + url + "\nparams:" + params);
        Request oKRequest = builder.url(url)
                .post(formBuilder.build())
                .build();
        return execute(oKRequest);
    }

    @Override
    public IResponse postJson(IRequest request) {
        // 指定请求方式
        request.setMethod(IRequest.POST);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        //添加参数
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(request.getParams()));

        Request.Builder builder = new Request.Builder();
        //添加header
        Map<String, String> header = request.getHeader();
        for (Map.Entry<String, String> m : header.entrySet()) {
            builder.header(m.getKey(), m.getValue());
        }
        String url = request.getUrl();
        LogUtils.i(TAG, "--get postjson:" + url + "\nparams:" + request.getParams());
        Request oKRequest = builder.url(url).post(body).build();
        return execute(oKRequest);
    }

    @Override
    public IResponse get(IRequest request, boolean forceCache) {
        // TODO: 2017/10/16 使用缓存，待实现
        return null;
    }

    @Override
    public IResponse post(IRequest request, boolean forceCache) {
        // TODO: 2017/10/16 使用缓存，待实现
        return null;
    }

    @Override
    public IResponse postJson(IRequest request, boolean forceCache) {
        // TODO: 2017/10/16 使用缓存，待实现
        return null;
    }

    /**
     * 请求执行过程
     *
     * @param request
     * @return
     */
    private IResponse execute(Request request) {
        BaseResponse commonResponse = new BaseResponse();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            // 设置状态码
            commonResponse.setCode(response.code());
            String body = response.body().string();
            if (Const.DEBUG) LogUtils.i(TAG, "body:" + body);
            // 设置响应数据
            commonResponse.setData(body);
        } catch (IOException e) {
            e.printStackTrace();
            commonResponse.setCode(commonResponse.STATE_UNKNOWN_ERROR);
            commonResponse.setData(e.getMessage());
        }
        return commonResponse;
    }

}
