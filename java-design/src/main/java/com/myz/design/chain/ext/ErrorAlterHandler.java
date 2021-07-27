package com.myz.design.chain.ext;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 16:02
 */
public class ErrorAlterHandler extends AlterHandler {
    public ErrorAlterHandler(AlterRule alterRule, Notification notification) {
        super(alterRule, notification);
    }

    @Override
    public void check(ApiStat apiStat) {
        if (apiStat.getErrorCount() > alterRule.getMatchedRule(apiStat.getApi()).getMaxTps()) {
            this.notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }

    }
}

