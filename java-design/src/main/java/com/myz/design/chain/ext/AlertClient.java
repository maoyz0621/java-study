package com.myz.design.chain.ext;

import com.myz.design.chain.ext.handler.ErrorAlterHandler;
import com.myz.design.chain.ext.handler.TimeoutAlterHandler;
import com.myz.design.chain.ext.handler.TpsAlterHandler;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 17:44
 */
public class AlertClient {

    public static void main(String[] args) {
        Alter alter = new Alter();

        ApiStat apiStat = new ApiStat();
        AlterRule alterRule = new AlterRule();
        Notification notification = new Notification();
        alter.addAlterHandlers(new TpsAlterHandler(alterRule, notification));
        alter.addAlterHandlers(new ErrorAlterHandler(alterRule, notification));
        alter.addAlterHandlers(new TimeoutAlterHandler(alterRule, notification));
        alter.check(apiStat);
    }
}
