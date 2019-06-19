package com.myz.java.study.jdbc;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将结果包装为Object类型
 *
 * @author myz
 */
public class JdbcObjectTest extends JdbcDemo {

    /**
     * 将一条查询结果封装为Object
     *
     * @throws SQLException
     */
    @Test
    public void testObject() throws SQLException {
        Object[] obj = null;
        sql = "SELECT * FROM t_user WHERE uuid = ?";
        //4 执行sql语句,预编译
        ps = conn.prepareStatement(sql.trim());
        ps.setObject(1, 2);

        //5  查询结果,遍历
        rs = ps.executeQuery();

        while (rs.next()) {
            obj = new Object[3];
            obj[0] = rs.getString("uuid");
            obj[1] = rs.getString("username");
            obj[2] = rs.getString("age");
        }
        System.out.println(Arrays.toString(obj));
    }

    /**
     * 将多条查询结果封装为Object
     *
     * @throws SQLException
     */
    @Test
    public void testListObject() throws SQLException {
        List<Object[]> lists = new ArrayList<Object[]>();
        Object[] obj = null;
        sql = "SELECT * FROM t_user WHERE uuid < ?";
        //4 执行sql语句,预编译
        ps = conn.prepareStatement(sql.trim());
        ps.setObject(1, 4);

        //5  查询结果,遍历
        rs = ps.executeQuery();

        while (rs.next()) {
            obj = new Object[3];
            obj[0] = rs.getString("uuid");
            obj[1] = rs.getString("username");
            obj[2] = rs.getString("age");

            lists.add(obj);
        }

        for (Object[] list : lists) {
            System.out.println(Arrays.toString(list));
        }

    }

}
