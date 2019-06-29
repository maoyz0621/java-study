package com.myz.spring.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DogStaticFactory
 * @Description: 实例工厂的方法，即先需要创建工厂本身，
 * 再调用工厂的实例方法来返回bean的实例 
 * @author xuwt
 * @date 2017年7月22日 下午12:39:59
 *
 */
public class DogInstanceFactory {
	private static Map<String, Dog> dogs = null;
	
	public DogInstanceFactory() {
		dogs = new HashMap<>();
		dogs.put("xiaohei", new Dog("xiaohei", 200d));
		dogs.put("xiaohua", new Dog("xiaohua", 300d));		
	}

	public Dog getDog(String name) {
		return dogs.get(name);
	}

}
