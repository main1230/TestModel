package com.zzl.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by zhangzl
 * 时间： 2017/10/28 20:46
 * 描述：
 */

object ApiFactory {

    /**
     * baseUrl of each Api
     */
    @Target(AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BaseUrl(val value: String)

    var okhttpClient = OkHttpFactory.okhttp

    private val mRetrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    fun <T> getApi(clazz: Class<T>): T {
        val baseUrl = clazz.getAnnotation(BaseUrl::class.java).value
        if (baseUrl.isEmpty())
            throw IllegalArgumentException("@BaseUrl of ${clazz.simpleName} is empty")
        return mRetrofitBuilder.baseUrl(baseUrl).build().create(clazz)
    }
}