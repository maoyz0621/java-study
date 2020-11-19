package com.myz.java.study.jdbc;
/**
 * @author myz
 */

import org.junit.Test;

import java.sql.SQLException;

public class JdbcBatchDemo extends JdbcDemo {

    /**
     * 测试批量插入结果
     *
     * @throws SQLException
     */
    @Test
    public void testBatch() throws SQLException {
        sql = "INSERT INTO t_user(username, age, sex) VALUES(?, ?, ?)";
        //4 执行sql语句,预编译
        ps = conn.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            ps.setObject(1, "mao" + i);
            ps.setInt(2, 12);
            ps.setString(3, "1");
            ps.addBatch();
        }
        ps.executeBatch();
        conn.commit();

    }

}
