## 建造者(Builder)模式:  
  建造者模式也叫生成器模式，他可以将一个产品对象的内部表象与产品的产生过程分离开来，从而使一个建造过程生成具有不同内部表象的产品对象。简单的说建造产品的过程，就是“组合零件”的过程，这些零件就相当于产品对象的内部表象，但是因为组合零件的过程十分复杂。因此，这个过程往往被“外部化”到一个Builder对象去，Builder返回给用户一个全部零件构建完毕的产品对象。


### 1 建造者角色(Builder)：
    一个接口或抽象类，用以规范产品对象的各个组成成分的建造，独立于应用程序的商业逻辑。具体建造者必须实现接口。

### 2 具体建造者(Concrete Builder)： 
    需要实现建造者角色接口，用于创建产品实例，并提供创建的产品实例。

### 3 指导者角色(Director)：
    调用ConcreteBuilder类创建对象，其本身并没有产品对象类的具体内容。

### 4 产品角色(Product)：
    该模式中要创建的复杂对象。

Director是用于和客户端打交道的角色，他将客户端创建产品的请求划分为对各个零件的创建请求，
再将这些请求交给Concrete Builder，Concrete Builder的创建过程对于客户端来说是隐藏的。
