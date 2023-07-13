package com.myz.design.chain.ext.handler;

import com.myz.design.chain.ext.AlterRule;
import com.myz.design.chain.ext.ApiStat;
import com.myz.design.chain.ext.Notification;
import org.springframework.util.Assert;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 15:42
 */
public abstract class AlterHandler {

    protected AlterRule alterRule;
    protected Notification notification;

    public AlterHandler(AlterRule alterRule, Notification notification) {
        Assert.notNull(alterRule, "AlterRule can not null");
        Assert.notNull(notification, "Notification can not null");
        this.alterRule = alterRule;
        this.notification = notification;
    }

    public void doCheck(ApiStat apiStat) {
        if (apiStat == null) {
            throw new RuntimeException("apiStat param null");
        }

        check(apiStat);
    }

    /**
     * 判断警报的规则
     *
     * @param apiStat
     */
    abstract void check(ApiStat apiStat);
}
