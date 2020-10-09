/**
 * 状态模式
 * 模式定义：当一个对象内在状态改变时允许其改变行为，这个对象看起来像改变了其类
 * 状态和行为一一对应的，状态之间是可以相互转化的；当对象的内在状态改变时，允许改变其行为
 * <p>
 * https://tech.meituan.com/2020/03/19/design-pattern-practice-in-marketing.html
 * <p>
 * 在接收到订单消息后，用户进入待校验状态；
 * 在校验后，若校验通过，用户进入预返奖状态，并放入延迟队列。若校验未通过，用户进入不返奖状态，结束流程；
 * T+N天后，处理延迟消息，若用户未退款，进入待返奖状态。若用户退款，进入失败状态，结束流程；
 * 执行返奖，若返奖成功，进入完成状态，结束流程。若返奖不成功，进入待补偿状态；
 * 待补偿状态的用户会由任务定期触发补偿机制，直至返奖成功，进入完成状态，保障流程结束。
 *
 * @author maoyz
 */
package com.myz.design.state.reward;