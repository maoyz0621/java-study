##### 抽象模板（Abstract Template）角色：
　　1 定义若干个抽象方法，由子类重写。定义若干个空实现的钩子方法，由子类重写。定义若干个已实现的具体方法，不由子类重写。其中，基本方法包括抽象方法（Abstract Method）、钩子方法（Hook Method）和具体方法（Concrete Method）。

　　2 定义若干个基本方法组合而成的模板方法，构成顶级逻辑。

##### 具体模板（Concrete Template）角色：
　　1 重写父类即抽象类的抽象方法和钩子方法
    


AbstractTemplate是一个抽象类，有三个方法:
  abstractMethod抽象方法由抽象类声明为抽象方法，并由子类实现；
  hookMethod钩子方法由抽象类声明并提供默认实现，由子类重写;
  concreteMethod具体方法由抽象类声明并实现。

模板模式的关键：
  子类可以置换父类的可变部分，但是不可以改变模板方法代表的顶级逻辑。



 