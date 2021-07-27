package com.myz.design.chain.ext;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 16:02
 */
public class TpsAlterHandler extends AlterHandler {

    public TpsAlterHandler(AlterRule alterRule, Notification notification) {
        super(alterRule, notification);
    }

    @Override
    public void check(ApiStat apiStat) {
        long tps = apiStat.getRequestCount() / apiStat.getDurationOfSeconds();
        if (tps > alterRule.getMatchedRule(apiStat.getApi()).getMaxTps()) {
            this.notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }
}