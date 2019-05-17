/**   
* @Title: EmpService.java
* @Package com.myz.java.study.jdbc.service.impl
* @Description: TODO
* @author myz
* @date 2017年6月14日 下午1:29:26
* @version V1.0   
*/
package com.myz.java.study.jdbc.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.myz.java.study.jdbc.bean.Emp;
import com.myz.java.study.jdbc.dbc.DBConnection;
import com.myz.java.study.jdbc.factory.DAOFactory;
import com.myz.java.study.jdbc.service.IEmpService;


/**
* @ClassName: EmpService
* @Description: TODO
* @author xuwt
* @date 2017年6月14日 下午1:29:26
* 
*/
public class EmpService implements IEmpService {
	//类内部提供一个数据库连接类的梳理对象
	private DBConnection dbc = new DBConnection();
	private Connection conn = null ;

	@Override
	public boolean insert(Emp vo) throws SQLException {
		try {
			this.conn = dbc.getConnection();
			//首先判断雇员编号是否存在,调用IEmpDAO.getById();
			if (DAOFactory.getIEmpDAOInstance(this.conn).findById(vo.getEmpno()) == null) {
				//不存在,使用雇员的IEmpDAO.doCreate()方法,返回操作的结果
				return DAOFactory.getIEmpDAOInstance(this.conn).doCreate(vo);
			}
			//否则返回false
			return false;
		} catch (SQLException e) {
			throw e;
		}finally {
			this.dbc.closeConnection();
		}
	}

	@Override
	public boolean update(Emp vo) throws SQLException {
		try {
			this.conn = dbc.getConnection();
			return DAOFactory.getIEmpDAOInstance(this.conn).doUpdate(vo);
		}
		catch (SQLException e) {
			throw e;
		}finally {
			this.dbc.closeConnection();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws SQLException {
		try {
			this.conn = dbc.getConnection();
			return DAOFactory.getIEmpDAOInstance(this.conn).doRemoveBatch(ids);
		} catch (SQLException e) {
			throw e;
		}finally {
			this.dbc.closeConnection();
		}
	}

	@Override
	public Emp get(Integer id) throws SQLException {
		try {
			this.conn = dbc.getConnection();
			return DAOFactory.getIEmpDAOInstance(this.conn).findById(id);
		} catch (SQLException e) {
			throw e;
		}finally {
			this.dbc.closeConnection();
		}
	}

	@Override
	public List<Emp> list() throws SQLException {
		try {
			this.conn = dbc.getConnection();
			return DAOFactory.getIEmpDAOInstance(this.conn).findAll();
		} catch (SQLException e) {
			throw e;
		}finally {
			this.dbc.closeConnection();
		}
	}

	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		try {
			this.conn = dbc.getConnection();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("信息:", DAOFactory.getIEmpDAOInstance(this.conn).
					findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("数量:", DAOFactory.getIEmpDAOInstance(this.conn).
					getAllCount(column, keyWord));			
			return map;
		} catch (SQLException e) {
			throw e;
		}finally {
			this.dbc.closeConnection();
		}
	}
}
