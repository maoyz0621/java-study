package com.myz.design.chain.ext;

/**
 * 报警rule
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 15:43
 */
public class AlterRule {

    private String matchedRule;
    private long maxTps;
    private int maxTimeoutCount;

    public String getMatchedRule() {
        return matchedRule;
    }

    public void setMatchedRule(String matchedRule) {
        this.matchedRule = matchedRule;
    }

    public long getMaxTps() {
        return maxTps;
    }

    public void setMaxTps(long maxTps) {
        this.maxTps = maxTps;
    }

    public AlterRule getMatchedRule(String api) {
        return null;
    }

    public int getMaxTimeoutCount() {
        return maxTimeoutCount;
    }

    public void setMaxTimeoutCount(int maxTimeoutCount) {
        this.maxTimeoutCount = maxTimeoutCount;
    }
}
