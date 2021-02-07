package com.myz.java.study.base.string;

/**
 * ObjectDemo
 * ==与equals(),前者比较的是引用变量储存的地址值,后者比较的是内容
 *
 * @author xuwt
 * @date 2017年6月9日 下午7:50:45
 */
public class ObjectDemo {

    private Integer num;
    private String str;

    public ObjectDemo() {
    }

    public ObjectDemo(Integer num1, String str) {
        this.num = num1;
        this.setStr(str);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((num == null) ? 0 : num.hashCode());
        result = prime * result + ((str == null) ? 0 : str.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ObjectDemo other = (ObjectDemo) obj;
        if (num == null) {
            if (other.num != null) {
                return false;
            }
        } else if (!num.equals(other.num)) {
            return false;
        }
        if (str == null) {
            if (other.str != null) {
                return false;
            }
        } else if (!str.equals(other.str)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "重写了toString()方法";
    }

    public static void main(String[] args) {
        ObjectDemo obj = new ObjectDemo(12, "demo");
        ObjectDemo obj2 = new ObjectDemo(12, "demo");
        System.out.println(obj.hashCode());
        System.out.println(obj == obj2);
        System.out.println(obj.equals(obj2));
    }


}
