FSM - 有限状态机（Finite-state machine）

1、定义状态和事件枚举

2、状态机定义所有的状态和初始状态

3、状态机定义状态的迁移动作

4、状态机的监听器

 - Transition: 节点，是组成状态机引擎的核心
 - source：节点的当前状态
 - target：节点的目标状态
 - event：触发节点从当前状态到目标状态的动作
 - guard：起校验功能，一般用于校验是否可以执行后续action
 - action：用于实现当前节点对应的业务逻辑处理

https://www.cnblogs.com/Zero-Jo/p/14151146.html