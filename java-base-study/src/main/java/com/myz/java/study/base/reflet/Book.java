package com.myz.java.study.base.reflet;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/7 16:45
 */
public class Book {
    private String title;
    private double price;

    public Book() {
        super();
        System.out.println("Book");
    }

    private Book(String title) {
        this.title = title;
        System.out.println(this.title);
    }

    public Book(String title, double price) {
        super();
        this.title = title;
        this.price = price;
        System.out.println(this.title + ":" + this.price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private void desc(int a, int b) {
        System.out.println("描述" + a + b);
    }

    public void mail(int a, int b) {
        System.out.println("描述" + a + b);
    }

    @Override
    public String toString() {
        return this.title + this.price;
    }
}