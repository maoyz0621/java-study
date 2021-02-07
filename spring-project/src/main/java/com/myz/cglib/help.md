### 基于类的动态代理CGLIB

1 目标对象，无需实现接口;

2 目标对象拦截器，实现MethodInterceptor<I>接口,通过methodProxy.invokeSuper(obj, objects)调用目标方法;

3 按照以下步骤执行:

    Enhancer enhancer = new Enhancer();
    // 1 设置父类,即目标对象
    enhancer.setSuperclass(TargetObject.class);
    // 2 设置回调,即目标对象拦截器
    enhancer.setCallback(new TargetInterceptor());
    // 3 对象创建
    TargetObject targetObject = (TargetObject) enhancer.create();
    // 4 执行目标对象方法
    targetObject.method("1");