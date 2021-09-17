package com.myz.design.strategy.paystrategyextend;

/**
 * 卡类型
 *
 * @author maoyz on 18-1-6.
 */
public enum CardEnum implements BaseEnum<Integer> {

    /**
     * 信用卡，编号１
     */
    XingYongCard("信用卡", 1),

    /**
     * 借记卡,编号2
     */
    JieJiCard("借记卡", 2);

    private String name;
    private int index;

    CardEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }

    public static void main(String[] args) {
        System.out.println(XingYongCard.name + XingYongCard.name() + XingYongCard.index + XingYongCard.ordinal());
    }

    @Override
    public Integer getCode() {
        return index;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public boolean is(Integer integer) {
        return false;
    }
}
