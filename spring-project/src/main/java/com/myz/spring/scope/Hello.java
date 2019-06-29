package com.myz.spring.scope;
/**
* @ClassName: Hello
* @Description: TODO
* @author xuwt
* @date 2017年7月18日 下午9:35:45
*
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
