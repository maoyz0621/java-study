package com.myz.design.chain.ext;

import com.alibaba.fastjson.JSON;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 15:42
 */
public class ApiStat {

    /**
     * 接口名称
     */
    private String api;
    /**
     * 请求个数
     */
    private long requestCount;
    /**
     * 错误数量
     */
    private long errorCount;
    /**
     * 持续时间
     */
    private long durationOfSeconds;
    /**
     * 超时次数
     */
    private int timeoutCount;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getDurationOfSeconds() {
        return durationOfSeconds;
    }

    public void setDurationOfSeconds(long durationOfSeconds) {
        this.durationOfSeconds = durationOfSeconds;
    }

    public int getTimeoutCount() {
        return timeoutCount;
    }

    public void setTimeoutCount(int timeoutCount) {
        this.timeoutCount = timeoutCount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
