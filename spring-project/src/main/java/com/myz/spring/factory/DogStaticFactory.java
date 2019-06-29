package com.myz.spring.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DogStaticFactory
 * @Description: 静态工厂方法 直接调用类的静态方法，可以直接返回bean的实例
 * @author xuwt
 * @date 2017年7月22日 下午12:39:59
 *
 */
public class DogStaticFactory {
	private static Map<String, Dog> dogs = new HashMap<>();
	
	static {
		dogs.put("xiao hei", new Dog("xiaohei", 200d));
		dogs.put("xiao hua ", new Dog("xiaohua", 300d));
	}

	public static Dog getDog(String name) {
		return dogs.get(name);
	}

}
