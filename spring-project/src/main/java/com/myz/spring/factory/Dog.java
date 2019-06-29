package com.myz.spring.factory;

public class Dog {
	private String name;
	private double price;

	public Dog() {
	}

	public Dog(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Dog [name=" + name + ", price=" + price + "]";
	}

}
