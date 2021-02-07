package com.myz.java.study.jdbc.factory;
/**
* @ClassName: DAOFactory
* @Description: 工厂模式
* @author myz
* @date 2017年6月6日 下午9:40:09
*/

import java.sql.Connection;
import com.myz.java.study.jdbc.dao.IEmpDAO;
import com.myz.java.study.jdbc.dao.impl.EmpDAO;

public class DAOFactory {

	/**
	* @Title: getIEmpDAOInstance
	* @Description: 获取DAO接口实例对象
	* @param: conn
	* @return: IEmpDAO   
	* @throws:
	 */
	public static IEmpDAO getIEmpDAOInstance(Connection conn){
		//返回接口的子类
		return new EmpDAO(conn);
	}

}
