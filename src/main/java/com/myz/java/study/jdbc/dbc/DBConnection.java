package com.myz.java.study.jdbc.dbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**   
* @Title: DBConnection.java
* @Package com.myz.java.study.jdbc.dbc
* @Description:
* 提供数据库连接和关闭的方法
* 构造方法中进行数据库驱动的加载和连接对象
* @author myz
* @date 2017年6月5日 下午10:53:56
* @version V1.0   
*/
public class DBConnection {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/javadb";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private Connection connection = null;
	
	/**
	 * 构造方法中实例化出Connection对象
	 * */
	public DBConnection() {
		try {
			Class.forName(DRIVER);
			this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
			// 关闭自动提交事务
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 取得数据库连接
	 * @return Connetion对象
	 */
	public Connection getConnection(){
		return this.connection;
	}
	
	/**
	 * 关闭数据库操作
	 */
	public void closeConnection(){
		if (this.connection != null) {	//判断数据库是否已连接
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
