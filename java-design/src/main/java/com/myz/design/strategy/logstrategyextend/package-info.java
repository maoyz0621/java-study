/**
 * 策略模式
 *
 * 案例：
 * 　　日志记录
 *
 * 　　策略模式来结合模板方法模式，也就是主要的结构是策略模式，局部实现上采用模板方法模式。
 * 通过这个示例也可以看出来，模式之间的结合是没有定势的，要具体问题具体分析。到数据库中。对于这样的功能的设计，
 * 就可以采用策略模式，把日志记录到数据库和日志记录到文件当作两种记录日志的策略，然后在运行期间根据需要进行动态
 * 的切换。
 * @author maoyz on 18-1-6.
 */
package com.myz.design.strategy.logstrategyextend;