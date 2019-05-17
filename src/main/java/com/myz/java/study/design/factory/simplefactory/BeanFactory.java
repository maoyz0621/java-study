package com.myz.java.study.design.factory.simplefactory;

/**
*  工厂方法
* 1  根据全限定名创建对象
* 2  利用反射创建
* 3  将工厂设计成单例模式
* 4  使用方法：BeanFactory.INSTANCE.getBean();
* @author myz
 */
class BeanFactory{

	private BeanFactory() {
	}
	
	/**
	* 提供生成对象的方法
	* 1  采用泛型方法，返回类型由传入类型决定
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String className, Class<T> checkType) {
		try {
			// 反射获取Class实例
			Class<T> clz = (Class<T>) Class.forName(className);
			// 反射实例化对象
			Object obj = clz.newInstance();

			if (!checkType.isInstance(obj)) {
				throw new Exception("类型不符");
			}
			// 返回对象
			return (T) obj;
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return null;
	}
}
