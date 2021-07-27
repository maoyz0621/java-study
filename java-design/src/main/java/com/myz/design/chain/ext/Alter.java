package com.myz.design.chain.ext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 15:42
 */
public class Alter {

    private List<AlterHandler> alterHandlers = new ArrayList<>();

    public void addAlterHandlers(AlterHandler alterHandler) {
        this.alterHandlers.add(alterHandler);
    }

    public void check(ApiStat apiStat) {
        for (AlterHandler alterHandler : alterHandlers) {
            alterHandler.doCheck(apiStat);
        }
    }
}
