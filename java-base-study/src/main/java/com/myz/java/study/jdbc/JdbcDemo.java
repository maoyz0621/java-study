package com.myz.java.study.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcDemo {
    protected static String sql;
    protected Connection conn = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;

    /**
     * 通过Properties读取资源配置文件
     *
     * @param key
     * @return
     */
    private String getMsg(String key) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    private void getDriver() {

    }

    @Before
    public void before() {
        try {
            //1 加载驱动器
            Class.forName(this.getMsg("driverClassName"));
            //2 链接数据库
            conn = DriverManager.getConnection(this.getMsg("url"), this.getMsg("username"), this.getMsg("password"));
            // 关闭自动提交功能
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到驱动器");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("找不到你要的结果");
        }
    }

    @Test
    public void testUpdate() throws SQLException {
        //3 定义sql语句,使用?占位符
        sql = "INSERT INTO t_user(username, age, sex) VALUES(?, ?, ?)";
        //4 执行sql语句,预编译,可以防止SQL注入
        ps = conn.prepareStatement(sql);
        //4.1 为SQL参数赋值
        // 为第一个?赋值
        // 序号从1开始
        ps.setObject(1, "mao14");
        ps.setInt(2, 20);
        ps.setString(3, "2");

        // executeUpdate()影响行数
        System.out.println("影响记录行：" + ps.executeUpdate());

        conn.commit();

    }

    @Test
    public void testQuery() throws SQLException {

        sql = "SELECT * FROM t_user where uuid=1";
        ps = conn.prepareStatement(sql);
        //5  查询结果,遍历executeQuery()
        rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("uuid");
            String name = rs.getString("username");
            String age = rs.getString("age");
            System.out.println("查询结果：" + id + "  " + name + "  " + age);
        }

        conn.commit();

    }

    @After
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
