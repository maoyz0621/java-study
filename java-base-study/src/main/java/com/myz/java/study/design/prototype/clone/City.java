package com.myz.java.study.design.prototype.clone;

import java.io.Serializable;

/**
 * @author maoyz
 */
public class City implements Cloneable, Serializable {

    private static final long serialVersionUID = 967483100203812519L;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public Object clone() {
        City city = null;
        try {
            city = (City) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
