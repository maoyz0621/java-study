package com.myz.java.study.jdbc.factory;

import com.myz.java.study.jdbc.service.IEmpService;
import com.myz.java.study.jdbc.service.impl.EmpService;

/**
* @ClassName: ServiceFactory
* @Description: TODO
* @author myz
* @date 2017年6月14日 下午1:30:21
* 
*/
public class ServiceFactory {
	public static IEmpService getIEmpServiceInstance() {
		return new EmpService();
	}
}
