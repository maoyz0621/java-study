package com.myz.spring.scope;

/**
 * @author maoyz0621
 */
public class Hello {
	private Integer id;

	public Hello() {
		System.out.println("执行Hello()");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hello [id=" + id + "]";
	}
	
}
