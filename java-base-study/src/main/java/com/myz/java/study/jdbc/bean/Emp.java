package com.myz.java.study.jdbc.bean;

import java.io.Serializable;
import java.util.Date;

/**   
* @Package com.myz.java.study.jdbc.vo
* @Description: 
* 简单类javabean
* 不要使用内置对象,使用包装类,因为其默认值为null
* 一定要实现Serializable接口
* @author xuwt
* @date 2017年6月5日 下午11:11:46
*/
@SuppressWarnings("serial")
public class Emp implements Serializable {
	private Integer empno;
	private String ename;
	private String job;
	private Date birthday;
	private Double sal;
	private Double comm;
	
	public Emp() {
	}

	public Emp(Integer empno, String ename, String job, Date birthday, Double sal, Double comm) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.birthday = birthday;
		this.sal = sal;
		this.comm = comm;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Double getComm() {
		return comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}
}
