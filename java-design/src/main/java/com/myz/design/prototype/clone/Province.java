package com.myz.design.prototype.clone;

import java.io.Serializable;

/**
 * @author maoyz
 */
public class Province implements Serializable {

    private static final long serialVersionUID = -7488649559418271046L;

    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Province{");
        sb.append("province='").append(province).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
