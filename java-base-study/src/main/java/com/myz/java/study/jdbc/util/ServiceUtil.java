package com.myz.java.study.jdbc.util;

import java.sql.SQLException;
import java.util.Date;

import com.myz.java.study.jdbc.bean.Emp;
import com.myz.java.study.jdbc.factory.ServiceFactory;

public class ServiceUtil {

	public static void main(String[] args) throws SQLException {	
		Emp emp = new Emp(23, "my11", "aa1111", new Date(), 1222.1, 1111.0);
		System.out.println(emp);
		
			boolean flag = ServiceFactory.getIEmpServiceInstance().update(emp);
			System.out.println(flag);

	}
}
