package com.myz.java.study.jdbc;

import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

/**
 * 将查询结果封装为map
 *
 * @author myz
 */
public class JdbcMapTest extends JdbcDemo {

    private Map<String, String> map = null;

    /**
     * 将一条结果封装为map
     *
     * @throws SQLException
     */
    @Test
    public void testMap() throws SQLException {
        sql = "SELECT * FROM t_user WHERE uuid = ?";
        //4 执行sql语句,预编译
        ps = conn.prepareStatement(sql.trim());
        ps.setObject(1, 2);

        //5  查询结果,遍历
        rs = ps.executeQuery();

        while (rs.next()) {
            map = new HashMap<>();
            map.put("uuid", rs.getString("uuid"));
            map.put("username", rs.getString("username"));
            map.put("age", rs.getString("age"));
        }

        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 将多条结果封装为map
     *
     * @throws SQLException
     */
    @Test
    public void testListMap() throws SQLException {
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();

        sql = "SELECT * FROM t_user WHERE uuid < ?";
        //4 执行sql语句,预编译
        ps = conn.prepareStatement(sql.trim());
        ps.setObject(1, 4);

        //5  查询结果,遍历
        rs = ps.executeQuery();

        while (rs.next()) {
            map = new HashMap<>();

            map.put("uuid", rs.getString("uuid"));
            map.put("username", rs.getString("username"));
            map.put("age", rs.getString("age"));

            lists.add(map);
        }

        for (Map<String, String> list : lists) {

            Set<Map.Entry<String, String>> entries = list.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            System.out.println("-------------");
        }

    }

}
