package com.myz.java.study.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.myz.java.study.jdbc.bean.Emp;
import com.myz.java.study.jdbc.dao.IEmpDAO;

/**
* @ClassName: EmpDAO
* @Description: TODO
* @author xuwt
* @date 2017年6月14日 下午1:13:15
 */
public class EmpDAO implements IEmpDAO{
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet result= null;
	private String sql = null;

	public EmpDAO(Connection connection) {
		//获取数据库连接对象
		this.connection = connection;
	}

	@Override
	public boolean doCreate(Emp vo) throws SQLException {
		this.sql = "INSERT INTO emp(empno, ename, job, birthday, sal, comm) VALUES (?, ?, ?, ?, ?, ?)";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setInt(1, vo.getEmpno());
		this.pstmt.setString(2, vo.getEname());
		this.pstmt.setString(3, vo.getJob());
		this.pstmt.setDate(4, new java.sql.Date(vo.getBirthday().getTime()));	//时间转换
		this.pstmt.setDouble(5, vo.getSal());
		this.pstmt.setDouble(6, vo.getComm());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Emp vo) throws SQLException {
		this.sql = "UPDATE emp SET ename=?, job=?, birthday=?, sal=?, comm=? WHERE empno=?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, vo.getEname());
		this.pstmt.setString(2, vo.getJob());
		this.pstmt.setDate(3, new java.sql.Date(vo.getBirthday().getTime()));	//时间转换
		this.pstmt.setDouble(4, vo.getSal());
		this.pstmt.setDouble(5, vo.getComm());
		this.pstmt.setInt(6, vo.getEmpno());		
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		if (ids == null || ids.size() == 0) {
			return false;
		}
		//拼接SQL语句
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM emp where empno in(");
		//遍历出Set中所有的id
		Iterator<Integer> iterator = ids.iterator();
		while (iterator.hasNext()) {
			sql.append(iterator.next()).append(",");			
		}
		sql.delete(sql.length()-1, sql.length()).append(")");	//删除最后一个","
		this.pstmt = this.connection.prepareStatement(sql.toString());
		return this.pstmt.executeUpdate() == ids.size();	//????
	}

	@Override
	public Emp findById(Integer id) throws SQLException {
		Emp vo = null;
		sql = "SELECT empno, ename, job, birthday, sal, comm FROM emp WHERE empno=?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		this.result = pstmt.executeQuery();
		if (this.result.next()) {
			vo = new Emp();
			vo.setEmpno(result.getInt(1));
			vo.setEname(result.getString(2));
			vo.setJob(result.getString(3));
			vo.setBirthday(result.getDate(4));
			vo.setSal(result.getDouble(5));
			vo.setComm(result.getDouble(6));			
		}
		return vo;	
	}

	@Override
	public Emp findByName(String name) throws SQLException {
		Emp vo = null;
		sql = "SELECT empno, ename, job, birthday, sal, comm FROM emp WHERE ename=?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, name);
		this.result = pstmt.executeQuery();
		if (this.result.next()) {
			vo = new Emp();
			vo.setEmpno(result.getInt(1));
			vo.setEname(result.getString(2));
			vo.setJob(result.getString(3));
			vo.setBirthday(result.getDate(4));
			vo.setSal(result.getDouble(5));
			vo.setComm(result.getDouble(6));			
		}
		return vo;	
	}

	@Override
	public List<Emp> findAll() throws SQLException {
		List<Emp> list = new ArrayList<Emp>();
		sql = "SELECT empno, ename, job, birthday, sal, comm FROM emp";
		this.pstmt = this.connection.prepareStatement(sql);
		this.result = this.pstmt.executeQuery();
		while(this.result.next()){
			Emp vo = new Emp();
			vo.setEmpno(result.getInt(1));
			vo.setEname(result.getString(2));
			vo.setJob(result.getString(3));
			vo.setBirthday(result.getDate(4));
			vo.setSal(result.getDouble(5));
			vo.setComm(result.getDouble(6));
			list.add(vo);
		}
		return list;
	}

	@Override
	public List<Emp> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		List<Emp> list = new ArrayList<Emp>();
		this.sql = "SELECT * FROM"
			+ "(SELECT empno, ename, job, birthday, sal, comm, ROWNUM rn "
			+ "FROM emp "
			+ "WHERE " + column + "LIKE ? AND ROWNUM <=?) temp "
			+ "WHERE temp.rn>?";
		this.pstmt = this.connection.prepareStatement(sql);
		
		this.pstmt.setString(1, "%"+ keyWord +"%");
		this.pstmt.setInt(2, currentPage * lineSize);
		this.pstmt.setInt(3, (currentPage-1) * lineSize);
		
		this.result = this.pstmt.executeQuery();
		while(this.result.next()){
			Emp vo = new Emp();
			vo.setEmpno(result.getInt(1));
			vo.setEname(result.getString(2));
			vo.setJob(result.getString(3));
			vo.setBirthday(result.getDate(4));
			vo.setSal(result.getDouble(5));
			vo.setComm(result.getDouble(6));
			list.add(vo);
		}
		return list;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException {
		this.sql = "SELECT COUNT(empno) FROM emp WHERE " + column +" LIKE ?";
		this.pstmt = this.connection.prepareStatement(sql);
		this.pstmt.setString(1, "%"+ keyWord +"%");
		this.result = this.pstmt.executeQuery();
		if (result.next()) {
			return result.getInt(1);
		}		
		return null;
	}
	
}
