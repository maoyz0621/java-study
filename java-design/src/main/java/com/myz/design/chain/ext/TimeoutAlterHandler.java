package com.myz.design.chain.ext;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 16:04
 */
public class TimeoutAlterHandler extends AlterHandler{

    public TimeoutAlterHandler(AlterRule alterRule, Notification notification) {
        super(alterRule, notification);
    }

    @Override
    public void check(ApiStat apiStat) {
        if (apiStat.getTimeoutCount() > alterRule.getMatchedRule(apiStat.getApi()).getMaxTimeoutCount()) {
            this.notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}
