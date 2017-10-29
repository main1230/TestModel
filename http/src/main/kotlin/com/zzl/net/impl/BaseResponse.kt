package com.zzl.net.impl

import com.zzl.net.IResponse

/**
 * Created by zhangzl
 * 时间： 2017/10/29 14:27
 * 描述：
 */
open class BaseResponse : IResponse {

    /**
     * 状态码
     */
    private var code = 0

    /**
     * 响应数据
     */
    private var data: String? = null

    override fun getCode(): Int {
        return code
    }

    override fun getData(): String {
        return data ?: "{}"
    }

    override fun setCode(code: Int) {
        this.code = code
    }

    override fun setData(data: String?) {
        this.data = data
    }
}