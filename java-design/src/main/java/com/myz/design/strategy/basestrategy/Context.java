package com.myz.design.strategy.basestrategy;

/**
 * 上下文对象，通常会持有一个具体的策略对象
 *
 * @author maoyz on 18-1-5.
 */
class Context {

    /**
     * 持有一个具体的策略对象
     */
    private Strategy strategy;

    public Context() {
    }

    /**
     * 构造方法中传入一个具体的策略对象
     *
     * @param strategy 　策略对象
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 上下文为客户端提供的操作接口，可以有参数和返回值
     */
    public void contextInterface() {
        //通常会转调具体的策略对象进行算法运算
        strategy.algorithmStrategy();
    }
}
