package com.zzl.top.common.http;

import com.zzl.top.Const;
import com.zzl.top.common.http.impl.BaseRequest;
import com.zzl.top.common.http.impl.OkHttpClientImpl;

import java.util.Map;

/**
 * Created by zhangzl
 * time: 2017/10/21 17:50.
 * 描述：网络请求
 */

public class OkHttpTools {

    public static IResponse post(IHttpClient iHttpClient, String url, Map<String, Object> params) {
        IRequest request = new BaseRequest(Const.URL + url);
        request.setParam(params);

        return iHttpClient.post(request);
    }

    public static IResponse get(IHttpClient iHttpClient, String url, Map<String, Object> params) {
        IRequest request = new BaseRequest(Const.URL + url);
        request.setParam(params);

        return iHttpClient.get(request);
    }

}
