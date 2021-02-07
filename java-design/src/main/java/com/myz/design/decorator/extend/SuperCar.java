package com.myz.design.decorator.extend;


import com.myz.design.decorator.ICar;

/**
 * @author xuwt
 */
public class SuperCar implements ICar {

    private ICar iCar = null;

    public SuperCar() {
    }

    /**
     * 将抽象对象传入
     */
    public SuperCar(ICar iCar) {
        this.iCar = iCar;
    }

    //重写方法
    @Override
    public String getDesc(String string) {
        return iCar.getDesc(string);
    }


}
