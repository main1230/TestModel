package com.zzl.net

import com.orhanobut.logger.Logger
import com.zzl.net.interceptor.HttpLoggingInterceptor
import com.zzl.net.utils.HttpsUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by zhangzl
 * 时间： 2017/10/28 21:05
 * 描述：
 */
object OkHttpFactory {

    private val CONNECT_TIMEOUT_SECONDS = 20L
    private val READ_TIMEOUT_SECONDS = 20L
    private val WRITE_TIMEOUT_SECONDS = 20L

    val okhttp: OkHttpClient by lazy { create() }

    fun create(interceptor: Interceptor? = null, showLog: Boolean = false): OkHttpClient {
        //val sslParams = HttpsUtil.getSslSocketFactory(null, null, null)
        val loggerInterceptor = HttpLoggingInterceptor{ chain, message ->
            Logger.d("okhttp-${chain.request().url().uri().path}", message)
        }.apply { this.level = HttpLoggingInterceptor.Level.BODY }

        return OkHttpClient.Builder()
                //.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .apply {
                    if(interceptor != null)
                        this.addInterceptor(interceptor)
                }
                .apply {
                    if(showLog)
                        this.addInterceptor(loggerInterceptor)
                }
                .build()
    }
}