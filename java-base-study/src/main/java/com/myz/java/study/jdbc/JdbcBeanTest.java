package com.myz.java.study.jdbc;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcBeanTest extends JdbcDemo {

    private User user = null;

    @Test
    public void testInsertBean() throws SQLException {
        user = new User("am", 12, 2);

        sql = "INSERT INTO t_user(username, age, sex) VALUES(?, ?, ?)";
        ps = conn.prepareStatement(sql);

        ps.setObject(1, user.getUsername());
        ps.setLong(2, user.getAge());
        ps.setString(3, String.valueOf(user.getSex()));
        System.out.println(ps.executeUpdate());
    }

    @Test
    public void testBean() throws SQLException {
        sql = "SELECT * FROM t_user WHERE uuid = ?";
        //4 执行sql语句,预编译
        ps = conn.prepareStatement(sql.trim());
        ps.setObject(1, 2);

        rs = ps.executeQuery();

        while (rs.next()) {
            user = new User();
            user.setUsername(rs.getString("username"));
            user.setAge(rs.getInt("age"));
            user.setSex(Integer.parseInt(rs.getString("sex")));
            System.out.println(user);
        }
    }

    @Test
    public void testListBean() throws SQLException {
        List<User> lists = new ArrayList<User>();

        sql = "SELECT * FROM t_user WHERE uuid < ?";
        //4 执行sql语句,预编译
        ps = conn.prepareStatement(sql.trim());
        ps.setObject(1, 4);

        rs = ps.executeQuery();

        while (rs.next()) {
            user = new User();

            user.setUsername(rs.getString("username"));
            user.setAge(rs.getInt("age"));
            user.setSex(Integer.parseInt(rs.getString("sex")));

            lists.add(user);
        }

        for (User list : lists) {
            System.out.println(list);
        }
    }

}
