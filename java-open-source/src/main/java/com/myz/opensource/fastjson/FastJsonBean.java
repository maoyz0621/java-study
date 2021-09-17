package com.myz.opensource.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/7 19:39
 */
@Data
public class FastJsonBean {

    @JSONField(deserializeUsing = MyFastJsonConfig.class, serializeUsing = MyFastJsonConfig.class)
    private String name;

}
